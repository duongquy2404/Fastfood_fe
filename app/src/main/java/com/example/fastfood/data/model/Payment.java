package com.example.fastfood.data.model;

import java.util.Date;

public class Payment {
    private Long id;
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
    private Date paymentDate;

    public Payment() {
    }

    public Payment(Long id, Long orderId, Double amount, PaymentStatus status, Date paymentDate) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}

enum PaymentStatus {
    PENDING,
    COMPLETED,
    FAILED,
    REFUNDED
}
