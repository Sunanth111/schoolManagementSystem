package com.school.repository;

import com.school.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByApplicantId(Long applicantId);
    List<LeaveRequest> findByStatus(LeaveRequest.Status status);
    List<LeaveRequest> findByApprovedById(Long approvedById);
}

