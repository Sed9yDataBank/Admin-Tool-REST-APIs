package com.example.jpastaffdata.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admins")
@Data
public class Admin extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String adminName;

    @NotNull
    @Size(max = 250)
    private String adminCompany;

    @NotNull
    private String adminUniversity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "admin")
    private Set<Staff> wholeStaff = new HashSet<>();

}
