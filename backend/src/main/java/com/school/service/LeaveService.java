package com.school.service;

import com.school.entity.LeaveRequest;
import com.school.entity.User;
import com.school.repository.LeaveRequestRepository;
import com.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private UserRepository userRepository;

    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        if (leaveRequest.getApplicant() != null && leaveRequest.getApplicant().getId() != null) {
            User applicant = userRepository.findById(leaveRequest.getApplicant().getId())
                    .orElseThrow(() -> new RuntimeException("Applicant not found"));
            leaveRequest.setApplicant(applicant);
        }

        // Calculate number of days
        if (leaveRequest.getStartDate() != null && leaveRequest.getEndDate() != null) {
            long days = ChronoUnit.DAYS.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()) + 1;
            leaveRequest.setNumberOfDays((int) days);
        }

        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getLeaveRequestsByApplicant(Long applicantId) {
        return leaveRequestRepository.findByApplicantId(applicantId);
    }

    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveRequestRepository.findByStatus(LeaveRequest.Status.PENDING);
    }

    public LeaveRequest approveLeaveRequest(Long id, Long approvedById) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        User approver = userRepository.findById(approvedById)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        leaveRequest.setStatus(LeaveRequest.Status.APPROVED);
        leaveRequest.setApprovedBy(approver);
        leaveRequest.setReviewedAt(java.time.LocalDateTime.now());

        return leaveRequestRepository.save(leaveRequest);
    }

    public LeaveRequest rejectLeaveRequest(Long id, Long approvedById, String remarks) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        User approver = userRepository.findById(approvedById)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        leaveRequest.setStatus(LeaveRequest.Status.REJECTED);
        leaveRequest.setApprovedBy(approver);
        leaveRequest.setRemarks(remarks);
        leaveRequest.setReviewedAt(java.time.LocalDateTime.now());

        return leaveRequestRepository.save(leaveRequest);
    }
}

