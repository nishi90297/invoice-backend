package com.bill.invoicebackend.utils;

import com.bill.invoicebackend.InvoiceBackendApplication;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Util {
    public static InputStream getFileFromResource(String fileName) throws Exception{
        try{
            return InvoiceBackendApplication.class.getResourceAsStream(File.separator+fileName);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static String convertFileToString(String fileName) throws Exception{
        try{
            return IOUtils.toString(getFileFromResource(fileName), StandardCharsets.UTF_8.name());
        } catch (Exception e){
        e.printStackTrace();
        throw e;
    }
    }
}
