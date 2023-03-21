package com.intern.hrmanagementapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private int gender;

    private String address;

    private LocalDateTime dob;

    private int departmentId;

    private int positionId;

    private int contractId;

    private int educationId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
