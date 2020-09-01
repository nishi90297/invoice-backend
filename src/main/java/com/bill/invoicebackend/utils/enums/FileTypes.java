package com.bill.invoicebackend.utils.enums;

public enum FileTypes {

    PDF("pdf"),
    XML("xml"),
    HTML("html");

    private String small;

    FileTypes(String small){
        this.small = small;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }
}
