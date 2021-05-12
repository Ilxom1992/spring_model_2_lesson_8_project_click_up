package com.example.demo.entity;

import com.example.demo.entity.Attachment;
import com.example.demo.entity.Tamplate.AbsUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentContent extends AbsUUIDEntity {

    private  byte[] asosiyContent;//ASOSIY CONTENT (MAG'ZI)
    @OneToOne
    private Attachment attachment;//QAYSI FILEGA TEGISHLI EKANLIGINI BILDIRADI
}
