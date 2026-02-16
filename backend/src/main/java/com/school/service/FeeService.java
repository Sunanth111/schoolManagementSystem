package com.school.service;

import com.school.entity.Fee;
import com.school.entity.Student;
import com.school.repository.FeeRepository;
import com.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    public Fee createFee(Fee fee) {
        if (fee.getStudent() != null && fee.getStudent().getId() != null) {
            Student student = studentRepository.findById(fee.getStudent().getId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            fee.setStudent(student);
        }

        if (fee.getPaidAmount() == null) {
            fee.setPaidAmount(0.0);
        }

        if (fee.getPaidAmount() >= fee.getAmount()) {
            fee.setStatus(Fee.Status.PAID);
            fee.setPaidDate(LocalDate.now());
        } else if (fee.getPaidAmount() > 0) {
            fee.setStatus(Fee.Status.PARTIAL);
        } else {
            fee.setStatus(Fee.Status.PENDING);
        }

        if (fee.getDueDate() != null && fee.getDueDate().isBefore(LocalDate.now()) && fee.getStatus() != Fee.Status.PAID) {
            fee.setStatus(Fee.Status.OVERDUE);
        }

        return feeRepository.save(fee);
    }

    public Fee updateFeePayment(Long id, Double paidAmount) {
        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee not found"));
        
        fee.setPaidAmount(paidAmount);
        
        if (paidAmount >= fee.getAmount()) {
            fee.setStatus(Fee.Status.PAID);
            fee.setPaidDate(LocalDate.now());
        } else if (paidAmount > 0) {
            fee.setStatus(Fee.Status.PARTIAL);
        } else {
            fee.setStatus(Fee.Status.PENDING);
        }

        return feeRepository.save(fee);
    }

    public List<Fee> getFeesByStudent(Long studentId) {
        return feeRepository.findByStudentId(studentId);
    }

    public List<Fee> getFeesByStatus(Fee.Status status) {
        return feeRepository.findByStatus(status);
    }
}

