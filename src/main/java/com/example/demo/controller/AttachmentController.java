package com.example.demo.controller;

import com.example.demo.entity.Attachment;
import com.example.demo.repository.AttachmentContentRepository;
import com.example.demo.repository.AttachmentRepository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;
         public AttachmentController(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }
private static final String uploadDirectory="yuklanganlar";

    //FILE SYSTEM GA SAQLASH
    @PostMapping("/uploadSytem")
    public String uploadFileSystem(MultipartHttpServletRequest request) throws IOException{
        Iterator<String> fileName=request.getFileNames();
        MultipartFile file = request.getFile(fileName.next());
        if (file !=null){
            String originalFilename = file.getOriginalFilename();
            Attachment attachment=new Attachment();
            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            String[] split = originalFilename.split("\\.");
            String name= UUID.randomUUID().toString()+"."+split[split.length-1];
            attachment.setName(name);
            attachmentRepository.save(attachment);
            Path  path= Paths.get(uploadDirectory+"/"+name);
            Files.copy(file.getInputStream(),path);
            return "Fayl saqlandi .Id si: "+attachment.getId();
        }
        return "Saqlanmadi";
    }

    //GET ALL ATTACHMENT
    @GetMapping(value = "/info")
    public List<Attachment> getAllAttachment(){
       return attachmentRepository.findAll();
    }
    //GET ATTACHMENT BY ID
    @GetMapping(value = "/info/{id}")
    public Optional<Attachment> getAttachment(@PathVariable UUID id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            return optionalAttachment;
        }
        return null;
    }
        //FILE SYSTEM DAN O'QISH
       @GetMapping("/download/{id}")
    public  void  getFileFromSystem(@PathVariable UUID id,HttpServletResponse response) throws IOException{
           Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
           if (optionalAttachment.isPresent()){
               Attachment attachment= optionalAttachment.get();
               response.setHeader("Content-Disposition","attachment;filename=\""+attachment.getFileOriginalName()+"\"");
               response.setContentType(attachment.getContentType());
               FileInputStream fileInputStream=new FileInputStream(uploadDirectory+"/"+attachment.getName());
               FileCopyUtils.copy(fileInputStream,response.getOutputStream());
           }
       }
}
