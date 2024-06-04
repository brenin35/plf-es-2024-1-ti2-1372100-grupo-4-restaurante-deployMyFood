package com.myfood.myfoodback.controllers;

import com.myfood.myfoodback.dto.PixPaymentResponseDTO;
import com.myfood.myfoodback.dto.PixPaymentDTO;
import com.myfood.myfoodback.services.PixPaymentService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/process_payment")
public class PixPaymentController {
    private final PixPaymentService pixPaymentService;

    public PixPaymentController(PixPaymentService pixPaymentService) {
        this.pixPaymentService = pixPaymentService;
    }

    @PostMapping
    public ResponseEntity<PixPaymentResponseDTO> processPayment(@RequestBody PixPaymentDTO pixPaymentDTO) {
        PixPaymentResponseDTO payment = pixPaymentService.processPayment(pixPaymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @GetMapping
    public ResponseEntity<List<PixPaymentResponseDTO>> getAllPayments() {
        List<PixPaymentResponseDTO> payments = pixPaymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
}
