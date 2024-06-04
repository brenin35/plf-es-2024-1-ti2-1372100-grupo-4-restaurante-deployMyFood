package com.myfood.myfoodback.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PixPaymentDTO {
    private BigDecimal transactionAmount;

    @JsonProperty("description")
    private String productDescription;

    private PayerDTO payer;

    public PixPaymentDTO() {
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public PayerDTO getPayer() {
        return payer;
    }

    public void setPayer(PayerDTO payer) {
        this.payer = payer;
    }
    @Override
    public String toString() {
        return "PixPaymentDTO{" +
                "transactionAmount=" + transactionAmount +
                ", productDescription='" + productDescription + '\'' +
                ", payer=" + payer +
                '}';
    }
}