package com.intern.hrmanagementapi.entity;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "firstName", nullable = false)
    @NotNull(message = "Employee firstName is required.")
    @NotBlank(message = "Employee firstName is not empty or blank.")
    private String firstName;
    @Column(name = "lastName", nullable = false)
    @NotNull(message = "Employee lastName is required.")
    @NotBlank(message = "Employee lastName is not empty or blank.")
    private String lastName;
    @Column(name = "gender", nullable = false)
    @NotNull(message = "Employee gender is required.")
    private int gender;
    @Column(name = "address", nullable = false)
    @NotNull(message = "Employee address is required.")
    @NotBlank(message = "Employee address is not empty or blank.")
    private String address;
    @Column(name = "dob", nullable = false)
    @NotNull(message = "Employee date of birth is required.")
    private LocalDateTime dob;
    @Column(name = "departmentId", nullable = false)
    @NotNull(message = "Employee departmentId is required.")
    private int departmentId;
    @Column(name = "positionId", nullable = false)
    @NotNull(message = "Employee positionId is required.")
    private int positionId;
    @Column(name = "contractId", nullable = false)
    @NotNull(message = "Employee contractId is required.")
    private int contractId;
    @Column(name = "educationId", nullable = false)
    @NotNull(message = "Employee educationId is required.")
    private int educationId;

    @NotNull(message = "Employee createDate is required.")
    private LocalDateTime createDate;
    @NotNull(message = "Employee updateDate is required.")
    private LocalDateTime updateDate;

}
