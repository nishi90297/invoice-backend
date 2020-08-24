package com.bill.invoicebackend.model.Invoice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(value = "Invoice")
public class Invoice {

    @Id
    private String id;
    private Instant createdAt;
    private String createdBy;
    private Double total;
    private InvoiceStatus invoiceStatus;
    private Integer statusCode;
    private InvoiceData invoiceData;

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", total=" + total +
                ", invoiceStatus=" + invoiceStatus +
                ", statusCode=" + statusCode +
                ", invoiceData=" + invoiceData +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public InvoiceStatus getStatus() {
        return invoiceStatus;
    }

    public void setStatus(InvoiceStatus status) {
        this.invoiceStatus = status;
        this.statusCode = invoiceStatus.getStatusCode();
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public InvoiceData getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(InvoiceData invoiceData) {
        this.invoiceData = invoiceData;
    }
}
