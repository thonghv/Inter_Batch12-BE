package com.intern.hrmanagementapi.entity;

import jakarta.persistence.*;
import jdk.jfr.MemoryAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Entity(name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @MemoryAddress
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String address;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "update_date")
    private Date updateDate;
}
