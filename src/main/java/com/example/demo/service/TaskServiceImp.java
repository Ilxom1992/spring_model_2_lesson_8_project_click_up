package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.payload.*;
import com.example.demo.repository.*;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService{
    private static final String uploadDirectory = "uploadedFiles";
    final TaskRepository taskRepository;
    final StatusRepository statusRepository;
    final CategoryRepositoryc categoryRepositoryc;
    final PriorityRepository priorityRepository;
    final CommitRepository commitRepository;
    final WorkspaceRepository workspaceRepository;
    final TagRepository tagRepository;
    final UserRepository userRepository;
    final TaskUserRepository taskUserRepository;
    final AttachmentRepository attachmentRepository;
    final TaskAttachmentRepository taskAttachmentRepository;

    public TaskServiceImp(TaskRepository taskRepository, StatusRepository statusRepository, CategoryRepositoryc categoryRepositoryc, PriorityRepository priorityRepository, CommitRepository commitRepository, WorkspaceRepository workspaceRepository, TagRepository tagRepository, UserRepository userRepository, TaskUserRepository taskUserRepository, AttachmentRepository attachmentRepository, TaskAttachmentRepository taskAttachmentRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.categoryRepositoryc = categoryRepositoryc;
        this.priorityRepository = priorityRepository;
        this.commitRepository = commitRepository;
        this.workspaceRepository = workspaceRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.taskUserRepository = taskUserRepository;
        this.attachmentRepository = attachmentRepository;
        this.taskAttachmentRepository = taskAttachmentRepository;
    }

    @Override
    public ApiResponse addTask(TaskDto taskDto) {
        Task task=new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        Optional<Status> optionalStatus = statusRepository.findById(taskDto.getStatusId());
        task.setStatus(optionalStatus.get());
        Task save = taskRepository.save(task);
        return new  ApiResponse("Task saqlandi",true,save);
    }

    @Override
    public ApiResponse editTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).get();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        Optional<Status> optionalStatus = statusRepository.findById(taskDto.getStatusId());
        task.setStatus(optionalStatus.get());
        Task save = taskRepository.save(task);
        return new  ApiResponse("Task saqlandi",true,save);

    }

    @Override
    public ApiResponse changeYourTaskStatus(Long statusId, StatusDto statusDto) {
        Optional<Status> optionalStatus = statusRepository.findById(statusId);
        Status status = optionalStatus.get();
        status.setColor(statusDto.getColor());
        status.setName(statusDto.getName());
        status.setType(statusDto.getType());
        statusRepository.save(status);
        return new  ApiResponse("Task statusi o'zgartirildi",true);
    }

    @Override
    public ApiResponse attachAFileToYourTask(MultipartHttpServletRequest request, TaskAttachmentDTO taskAttachmentDTO) throws IOException {
        final Iterator<String> fileNames = request.getFileNames();
        final MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            final String originalFilename = file.getOriginalFilename();
            Attachment attachment = new Attachment();
            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            assert originalFilename != null;
            final String[] split = originalFilename.split("\\.");
            final String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
            attachment.setName(name);

            final Attachment savedAttachment = attachmentRepository.save(attachment);
            final Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);

            final Optional<Task> optionalTask = taskRepository.findById(taskAttachmentDTO.getTaskId());
            if (!optionalTask.isPresent()) {
                return new ApiResponse("Task not found", false);
            }
            final Task task = optionalTask.get();

            TaskAttachment taskAttachment = new TaskAttachment(
                    task, savedAttachment, taskAttachmentDTO.isPinnedCoverPhoto()
            );
            taskAttachmentRepository.save(taskAttachment);

            return new ApiResponse("File saved. File id: " + attachment.getId(), true);
        }
        return new ApiResponse("File not saved", false);
    }

    @Override
    public ApiResponse deleteTheAttachedFile(Long taskId,UUID attachmentId) {
        final Optional<TaskAttachment> optionalTaskAttachment = taskAttachmentRepository.findByTaskIdAndAttachmentId(taskId, attachmentId);
        if (!optionalTaskAttachment.isPresent()) {
            return new ApiResponse("Not found", false);
        }

        final TaskAttachment taskAttachment = optionalTaskAttachment.get();

        taskAttachmentRepository.delete(taskAttachment);

        return new ApiResponse("Deleted", true);
    }
    @Override
    public ApiResponse addCommentToTask(Long taskId, CommentDto commentDto) {
        Task task = taskRepository.findById(taskId).get();
        Comment comment=new Comment();
        comment.setName(comment.getName());
        comment.setTask(task);
        commitRepository.save(comment);
        return new  ApiResponse("Commit Taskga Biriktirildi",true);
    }

    @Override
    public ApiResponse addTagToTask(Long workSpaceId, TagDto tagDto) {

        Workspace workspace = workspaceRepository.findById(workSpaceId).get();
        Tag tag=new Tag();
        tag.setColor(tag.getColor());
        tag.setName(tagDto.getName());
        tag.setWorkspace(workspace);
        tagRepository.save(tag);
        return new  ApiResponse("Tag biriktirildi",true);
    }

    @Override
    public ApiResponse changeTag(Long tagId,TagDto tagDto) {

        Tag tag = tagRepository.findById(tagId).get();
        tag.setColor(tag.getColor());
        tag.setName(tagDto.getName());
        tagRepository.save(tag);
        return new  ApiResponse("Tag biriktirildi",true);
    }

    @Override
    public ApiResponse deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);
        return new  ApiResponse("Tag o'chirildi",true);
    }

    @Override
    public ApiResponse assignAUserToATask(UUID userId, Long taskId) {
        TaskUser taskUser=new TaskUser();
        taskUser.setTask(taskRepository.findById(taskId).get());
        taskUser.setUser(userRepository.findById(userId).get());
        taskUserRepository.save(taskUser);
        return new  ApiResponse("Taskga user biriktirildi",true);

    }

    @Override
    public ApiResponse removeAUserToATask(UUID userId, Long taskId) {
        taskUserRepository.deleteByTaskIdAndUserId(taskId,userId);
          return new  ApiResponse("Taskdan user O'chirildi",true);
    }
}
