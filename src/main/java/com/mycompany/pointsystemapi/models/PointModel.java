package com.mycompany.pointsystemapi.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "point")
@Data
public class PointModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String employee;
    @Column(nullable = false, length = 30)
    private String project;
    @Column(nullable = false, length = 50)
    private String activity;
    @Column(length = 50)
    private String note;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime entry;
    @Column(nullable = false)
    private LocalTime exit;
}
