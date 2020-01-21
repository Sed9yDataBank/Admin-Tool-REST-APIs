package com.example.jpastaffdata.repository;

import com.example.jpastaffdata.model.Project;
import com.example.jpastaffdata.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByStaffId(Long staff, Pageable pageable);
    Optional<Project> findByIdAndStaffId(Long id, Long staffId);
}
