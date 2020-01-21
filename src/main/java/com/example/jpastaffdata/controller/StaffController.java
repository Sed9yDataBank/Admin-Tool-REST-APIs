package com.example.jpastaffdata.controller;

import com.example.jpastaffdata.exception.ResourceNotFoundException;
import com.example.jpastaffdata.model.Staff;
import com.example.jpastaffdata.repository.AdminRepository;
import com.example.jpastaffdata.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admins/{adminId}/wholeStaff")
    public Page<Staff> getAllStaffByAdminId(@PathVariable (value = "adminId") Long adminId,
                                            Pageable pageable) {
        return staffRepository.findByAdminId(adminId, pageable);
    }

    @PostMapping("/admins/{adminId}/wholeStaff")
    public Staff createStaff(@PathVariable (value = "adminId") Long adminId,
                             @Valid @RequestBody Staff staff) {
        return adminRepository.findById(adminId).map(admin -> {
            staff.setAdmin(admin);
            return staffRepository.save(staff);
        }).orElseThrow(() -> new ResourceNotFoundException("AdminId " + adminId + " not found"));
    }

    @PutMapping("/admins/{adminId}/wholeStaff/{staffId}")
    public Staff updateStaff(@PathVariable(value = "adminId") Long adminId,
                             @PathVariable(value = "staffId") Long staffId,
                             @Valid @RequestBody Staff staffRequest) {
        if (!adminRepository.existsById(adminId)) {
            throw new  ResourceNotFoundException("AdminId " + adminId + " not found");
        }
        return staffRepository.findById(staffId).map(staff -> {
            staff.setStaffName(staffRequest.getStaffName());
            return staffRepository.save(staff);
        }).orElseThrow(() -> new ResourceNotFoundException("StaffId " + staffId + " not found"));
    }

    @DeleteMapping("/admins/{adminId}/wholeStaff/{staffId}")
    public ResponseEntity<?> deleteStaff(@PathVariable (value = "adminId") Long adminId,
                                         @PathVariable (value = "staffId") Long staffId) {
        return staffRepository.findByIdAndAdminId(staffId, adminId).map(staff -> {
            staffRepository.delete(staff);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + staffId + " and adminId " + adminId));
    }
}
