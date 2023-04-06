package com.intern.hrmanagementapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.MemoryAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@Entity(name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentEntity {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @MemoryAddress
    @Column
    private int id;
    @Column
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @Column
    @NotNull(message = "address cannot be null")
    @NotEmpty(message = "address cannot be empty")
    private String address;
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;
    @UpdateTimestamp
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "delete_date")
    private  Date deletedate;
}
