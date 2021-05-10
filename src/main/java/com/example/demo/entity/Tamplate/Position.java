package com.example.demo.entity.Tamplate;

import com.example.demo.entity.Tamplate.AbstractEntity;
import com.example.demo.entity.enums.Huquq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Position extends AbstractEntity {

    @Column(unique = true,nullable = false)
    private  String name;//ADMIN,USER VA BOSHQALAR

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Huquq> huquqList;

    private  String descriptin;
}
