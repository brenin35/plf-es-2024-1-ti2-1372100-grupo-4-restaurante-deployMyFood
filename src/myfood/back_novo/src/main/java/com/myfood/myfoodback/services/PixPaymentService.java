package com.myfood.myfoodback.services;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.payment.Payment;
import com.myfood.myfoodback.dto.PixPaymentResponseDTO;
import com.myfood.myfoodback.exception.MercadoPagoException;
import com.myfood.myfoodback.dto.PixPaymentDTO;
import com.mercadopago.exceptions.MPException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PixPaymentService {
    @Value("${mercado_pago_sample_access_token}")
    private String mercadoPagoAccessToken;

    private final List<PixPaymentResponseDTO> paymentDatabase = new ArrayList<>();

    public PixPaymentResponseDTO processPayment(PixPaymentDTO pixPaymentDTO) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            PaymentClient paymentClient = new PaymentClient();

            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(pixPaymentDTO.getTransactionAmount())
                    .description(pixPaymentDTO.getProductDescription())
                    .paymentMethodId("pix")
                    .payer(
                            PaymentPayerRequest.builder()
                                    .email(pixPaymentDTO.getPayer().getEmail())
                                    .firstName(pixPaymentDTO.getPayer().getFirstName())
                                    .lastName(pixPaymentDTO.getPayer().getLastName())
                                    .identification(
                                            IdentificationRequest.builder()
                                                    .type(pixPaymentDTO.getPayer().getIdentification().getType())
                                                    .number(pixPaymentDTO.getPayer().getIdentification().getNumber())
                                                    .build())
                                    .build())
                    .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            PixPaymentResponseDTO responseDTO = new PixPaymentResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCode());
            paymentDatabase.add(responseDTO);

            return new PixPaymentResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCode());
        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new MercadoPagoException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new MercadoPagoException(exception.getMessage());
        }
    }

    public List<PixPaymentResponseDTO> getAllPayments() {
        return new ArrayList<>(paymentDatabase);
    }
}