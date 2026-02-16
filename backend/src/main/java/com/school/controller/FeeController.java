package com.school.controller;

import com.school.entity.Fee;
import com.school.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fees")
@CrossOrigin(origins = "*")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @GetMapping
    public List<Fee> getAllFees() {
        return feeService.getAllFees();
    }

    @PostMapping
    public Fee createFee(@RequestBody Fee fee) {
        return feeService.createFee(fee);
    }

    @PutMapping("/{id}/payment")
    public ResponseEntity<Fee> updateFeePayment(@PathVariable Long id, @RequestBody Map<String, Double> payment) {
        try {
            Double paidAmount = payment.get("paidAmount");
            Fee updated = feeService.updateFeePayment(id, paidAmount);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/student/{studentId}")
    public List<Fee> getFeesByStudent(@PathVariable Long studentId) {
        return feeService.getFeesByStudent(studentId);
    }

    @GetMapping("/status/{status}")
    public List<Fee> getFeesByStatus(@PathVariable Fee.Status status) {
        return feeService.getFeesByStatus(status);
    }
}

