package com.example.jpastaffdata.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admins")
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

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminCompany() {
        return adminCompany;
    }

    public void setAdminCompany(String adminCompany) {
        this.adminCompany = adminCompany;
    }

    public String getAdminUniversity() {
        return adminUniversity;
    }

    public void setAdminUniversity(String adminUniversity) {
        this.adminUniversity = adminUniversity;
    }

    public Set<Staff> getWholeStaff() {
        return wholeStaff;
    }

    public void setWholeStaff(Set<Staff> wholeStaff) {
        this.wholeStaff = wholeStaff;
    }
}
