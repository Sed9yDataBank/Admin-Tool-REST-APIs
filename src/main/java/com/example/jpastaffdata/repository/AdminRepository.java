package com.example.jpastaffdata.repository;

import com.example.jpastaffdata.exception.AdminResponse;
import com.example.jpastaffdata.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT new com.example.jpastaffdata.exception.AdminResponse(a.adminCompany, s.staffName) FROM Admin a JOIN a.wholeStaff s")
    public List<AdminResponse> getAllInformation();

}
