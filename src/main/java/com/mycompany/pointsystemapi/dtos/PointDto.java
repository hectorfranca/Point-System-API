package com.mycompany.pointsystemapi.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PointDto {
    @NotBlank
    @Size(max = 50)
    private String employee;
    @NotBlank
    @Size(max = 30)
    private String project;
    @NotBlank
    @Size(max = 50)
    private String activity;
    @Size(max = 50)
    private String note;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime entry;
    @NotNull
    private LocalTime exit;
}
