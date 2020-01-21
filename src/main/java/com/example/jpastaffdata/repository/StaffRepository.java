package com.example.jpastaffdata.repository;

import com.example.jpastaffdata.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Page<Staff> findByAdminId(Long staffId, Pageable pageable);
    Optional<Staff> findByIdAndAdminId(Long id, Long adminId);
}
