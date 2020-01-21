package com.example.jpastaffdata.controller;

import com.example.jpastaffdata.exception.ResourceNotFoundException;
import com.example.jpastaffdata.model.Admin;
import com.example.jpastaffdata.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admins")
    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @PostMapping("/admins")
    public Admin createAdmin(@Valid @RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @PutMapping("/admins/{adminId}")
    public Admin updateAdmin(@PathVariable Long adminId, @Valid @RequestBody Admin adminRequest) {
        return adminRepository.findById(adminId).map(admin -> {
            admin.setAdminName(adminRequest.getAdminName());
            admin.setAdminUniversity(adminRequest.getAdminUniversity());
            admin.setAdminCompany(adminRequest.getAdminCompany());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new ResourceNotFoundException("AdminId " + adminId + " not found"));
    }

    @DeleteMapping("/admins/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        return adminRepository.findById(adminId).map(admin -> {
            adminRepository.delete(admin);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("AdminId " + " not found"));
    }

}
