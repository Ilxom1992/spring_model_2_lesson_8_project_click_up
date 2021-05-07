package com.example.demo.payload;

import com.example.demo.entity.enums.Huquq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PositionDto {
    @NotBlank
    private String name;

    private String description;
    @NotEmpty
    private List<Huquq> huquqList;
}
