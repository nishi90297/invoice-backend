package com.bill.invoicebackend.model.Invoice;

public enum  InvoiceStatus {

    DATA_SAVED(101),
    CHANNEL_SELECTED(102),
    SENT(103);

    InvoiceStatus(int statusCode) {
        this.statusCode=statusCode;
    }

    private Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
