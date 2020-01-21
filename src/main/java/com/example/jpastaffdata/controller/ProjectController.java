package com.example.jpastaffdata.controller;

import com.example.jpastaffdata.exception.ResourceNotFoundException;
import com.example.jpastaffdata.model.Project;
import com.example.jpastaffdata.repository.ProjectRepository;
import com.example.jpastaffdata.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/wholeStaff/{staffId}/projects")
    public Page<Project> getAllProjectsByStaffId(@PathVariable(value = "staffId") Long staffId,
                                                 Pageable pageable) {
        return projectRepository.findByStaffId(staffId, pageable);
    }

    @PostMapping("/wholeStaff/{staffId}/projects")
    public Project createProject(@PathVariable (value = "staffId") Long staffId,
                                 @Valid @RequestBody Project project) {
        return staffRepository.findById(staffId).map(staff -> {
            project.setStaff(staff);
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("StaffId " + staffId + " not found"));
    }

    @PutMapping("/wholeStaff/{staffId}/projects/{projectId}")
    public Project updateProject(@PathVariable (value = "staffId") Long staffId,
                                 @PathVariable (value = "projectId") Long projectId,
                                 @Valid @RequestBody Project projectRequest) {
        if (!staffRepository.existsById(staffId)) {
            throw new ResourceNotFoundException("StaffId " + staffId + " not found");
        }
        return projectRepository.findById(projectId).map(project -> {
            project.setProjectName(projectRequest.getProjectName());
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("ProjectId " + projectId + " not found"));
    }

    @DeleteMapping("/wholeStaff/{staffId}/projects/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable (value = "staffId") Long staffId,
                                           @PathVariable (value = "projectId") Long projectId) {
        return projectRepository.findByIdAndStaffId(projectId, staffId).map(project -> {
            projectRepository.delete(project);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId + " and staffId " + staffId));
    }


}
