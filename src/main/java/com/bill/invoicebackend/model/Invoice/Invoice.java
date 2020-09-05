package com.bill.invoicebackend.model.Invoice;

import com.bill.invoicebackend.utils.enums.InvoiceStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(value = "Invoice")
public class Invoice {

    @Id
    private String id;
    private Number templateNo;

    private String invoiceNumber;

    //set in controller
    private Instant createdAt;
    private String createdBy;
    private InvoiceStatus invoiceStatus;
    private Integer statusCode;

    private String payerName;
    @Indexed(unique=true)
    private String payerEmail;
    private String payerAddress;
    private List<InvoiceProduct> products;

    private Instant dueDate;
    private String freeText;
    private String footer;
    private Number discount;
    private Double totalAmount;
    private Double discountedAmount;
    private Double finalAmount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(Number templateNo) {
        this.templateNo = templateNo;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
        this.setStatusCode(invoiceStatus.getStatusCode());
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public List<InvoiceProduct> getProducts() {
        return products;
    }

    public void setProducts(List<InvoiceProduct> products) {
        this.products = products;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Number getDiscount() {
        return discount;
    }

    public void setDiscount(Number discount) {
        this.discount = discount;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }
}
