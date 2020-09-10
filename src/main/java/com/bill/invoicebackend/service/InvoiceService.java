package com.bill.invoicebackend.service;

import com.bill.invoicebackend.model.Invoice.InvoiceProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    public String tableProductsRow(List<InvoiceProduct> invoiceProductList){
        String tableProductRow = "";

        for (int i=0;i<invoiceProductList.size();i++) {
             tableProductRow = tableProductRow+ "" +
                    "<tr>\n" +
                    "  <td class=\"no\">"+(i+1)+"</td>\n" +
                    "  <td class=\"desc\"><h3>"+ invoiceProductList.get(i).getName() +"</h3>"+ invoiceProductList.get(i).getDescription() +"</td>\n" +
                    "  <td class=\"unit\">"+ invoiceProductList.get(i).getPrice() +"</td>\n" +
                    "  <td class=\"qty\">"+ invoiceProductList.get(i).getQuantity() +"</td>\n" +
                    "  <td class=\"total\">"+ invoiceProductList.get(i).getTotal() +"</td>\n" +
                    "</tr>";
        }

        return tableProductRow;
    }
}
