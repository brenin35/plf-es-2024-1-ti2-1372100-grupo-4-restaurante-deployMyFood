package com.myfood.myfoodback.controllers;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentPixController {

  public PaymentPixController() {
    MercadoPagoConfig.setAccessToken("YOUR_ACCESS_TOKEN");
  }

  @PostMapping("/create_payment")
  public Payment createPayment(@RequestBody Map<String, Object> request) throws MPException {
    PaymentClient client = new PaymentClient();

    PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
        .transactionAmount(new BigDecimal((Integer) request.get("amount")))
        .token((String) request.get("token"))
        .description((String) request.get("description"))
        .installments(1)
        .paymentMethodId((String) request.get("paymentMethodId"))
        .payer(PaymentPayerRequest.builder().email((String) request.get("email")).build())
        .build();

    try {
      return client.create(createRequest);
    } catch (MPApiException ex) {
      throw new RuntimeException(String.format(
          "MercadoPago Error. Status: %s, Content: %s",
          ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent()));
    }
  }
}
