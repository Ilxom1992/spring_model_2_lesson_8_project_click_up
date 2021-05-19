package com.example.demo.payload;

import lombok.Data;

@Data
public class TaskAttachmentDTO {

    private Long taskId;

    private Long attachmentId;

    boolean isPinnedCoverPhoto;

}
