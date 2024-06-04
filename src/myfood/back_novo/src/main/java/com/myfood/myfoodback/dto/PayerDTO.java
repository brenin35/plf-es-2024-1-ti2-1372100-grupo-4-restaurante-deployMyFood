package com.myfood.myfoodback.dto;


public class PayerDTO {

    private String firstName;

    private String lastName;

    private String email;

    private PayerIdentificationDTO identification;

    public PayerDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PayerIdentificationDTO getIdentification() {
        return identification;
    }

    public void setIdentification(PayerIdentificationDTO identification) {
        this.identification = identification;
    }

    @Override
    public String toString() {
        return "PayerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", identification=" + identification +
                '}';
    }
}