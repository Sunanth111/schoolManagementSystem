package com.school.controller;

import com.school.entity.LeaveRequest;
import com.school.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "*")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping
    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        return leaveService.createLeaveRequest(leaveRequest);
    }

    @GetMapping("/applicant/{applicantId}")
    public List<LeaveRequest> getLeaveRequestsByApplicant(@PathVariable Long applicantId) {
        return leaveService.getLeaveRequestsByApplicant(applicantId);
    }

    @GetMapping("/pending")
    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveService.getPendingLeaveRequests();
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeaveRequest(
            @PathVariable Long id,
            @RequestBody Map<String, Long> request) {
        try {
            Long approvedById = request.get("approvedById");
            LeaveRequest leaveRequest = leaveService.approveLeaveRequest(id, approvedById);
            return ResponseEntity.ok(leaveRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeaveRequest(
            @PathVariable Long id,
            @RequestBody Map<String, Object> request) {
        try {
            Long approvedById = Long.valueOf(request.get("approvedById").toString());
            String remarks = request.get("remarks") != null ? request.get("remarks").toString() : "";
            LeaveRequest leaveRequest = leaveService.rejectLeaveRequest(id, approvedById, remarks);
            return ResponseEntity.ok(leaveRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

