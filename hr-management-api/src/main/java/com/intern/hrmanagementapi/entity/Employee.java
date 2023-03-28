package com.intern.hrmanagementapi.entity;

import jakarta.persistence.*;
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
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "gender", nullable = false)
    private int gender;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "dob", nullable = false)
    private LocalDateTime dob;
    @Column(name = "departmentId", nullable = false)
    private int departmentId;
    @Column(name = "positionId", nullable = false)
    private int positionId;
    @Column(name = "contractId", nullable = false)
    private int contractId;
    @Column(name = "educationId", nullable = false)
    private int educationId;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
