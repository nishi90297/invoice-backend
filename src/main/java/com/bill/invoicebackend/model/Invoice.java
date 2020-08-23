package com.bill.invoicebackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "Invoice")
public class Invoice {

    private String payerName;

    @Indexed(unique=true)
    private String payerEmail;

    private String freeText;
    private List<Product> products;
    private String footer;
    private Number discount;
    private Number total;

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

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public Number getTotal() {
        return total;
    }

    public void setTotal(Number total) {
        this.total = total;
    }
}
