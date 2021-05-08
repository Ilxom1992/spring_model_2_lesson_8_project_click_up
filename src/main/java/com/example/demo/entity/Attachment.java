package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileOriginalName;//PDP  JPG   PNG  PDF
    private Long size;
    private String contentType;//APPLICATION/PDF || IMAGE/PNG
    //BU FILE SISTEMGA SAQLAGANDA KERAK BO'LADI
    private String name;//DISKNI ICHIDAN TOPISH UCHUN
}