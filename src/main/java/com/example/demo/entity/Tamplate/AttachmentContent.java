package com.example.demo.entity.Tamplate;

import com.example.demo.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  byte[] asosiyContent;//ASOSIY CONTENT (MAG'ZI)
    @OneToOne
    private Attachment attachment;//QAYSI FILEGA TEGISHLI EKANLIGINI BILDIRADI
}
