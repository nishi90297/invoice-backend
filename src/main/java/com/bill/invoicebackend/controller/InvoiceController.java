package com.bill.invoicebackend.controller;

import com.bill.invoicebackend.model.Biller.Biller;
import com.bill.invoicebackend.model.Invoice.Invoice;
import com.bill.invoicebackend.utils.FilePath;
import com.bill.invoicebackend.utils.enums.FileTypes;
import com.bill.invoicebackend.utils.enums.InvoiceStatus;
import com.bill.invoicebackend.respository.BillerRepository;
import com.bill.invoicebackend.respository.InvoiceRepository;
import com.bill.invoicebackend.utils.Util;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.configurations.WrapperConfig;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    public BillerRepository billerRepository;

    @Autowired
    public InvoiceRepository invoiceRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Biller getBillerInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return billerRepository.findByEmail(authentication.getName());
    }

    @GetMapping(value = "getAllInvoices")
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findByCreatedBy(getBillerInfo().getId());
    }

    @GetMapping(value = "getInvoiceById/{id}")
    public Invoice getInvoiceById(@PathVariable("id") String id){
        return invoiceRepository.findById(id).get();
    }

    @GetMapping(value = "getInvoiceNumber")
    public Integer getInvoiceNumber(){
        List<Invoice> invoiceList = invoiceRepository.findByCreatedBy(getBillerInfo().getId());
        return invoiceList.size()+1;
    }

    @PostMapping(value = "addInvoiceForm")
    public String addInvoiceForm(@RequestBody Invoice invoice){
        invoice.setCreatedBy(getBillerInfo().getId());
        invoice.setCreatedAt(Instant.now());
        invoice.setInvoiceStatus(InvoiceStatus.DATA_SAVED);
        System.out.println(invoice.toString());
        invoiceRepository.save(invoice);
        generatePDF(invoice);
        return invoiceRepository.save(invoice).getId();
    }

    @GetMapping(value = "generatePDF")
    public void generatePDF(Invoice invoice){
        try{
            String fileStr = Util.convertFileToString("Template/invoices/invoice1.html")
                    .replace("$payerName$",invoice.getPayerName())
                    .replace("$payerAddress$",invoice.getPayerAddress())
                    .replace("$payerEmail$",invoice.getPayerEmail())
                    .replace("$invoiceNumber$",invoice.getInvoiceNumber())
                    .replace("$createdDate$",invoice.getCreatedAt().toString())
                    .replace("$dueDate$",invoice.getDueDate().toString())
                    .replace("$total$",invoice.getTotalAmount().toString())
                    .replace("$discount$",invoice.getDiscount().toString())
                    .replace("$discountedAmount$",invoice.getDiscountedAmount().toString())
                    .replace("$finalAmount$",invoice.getFinalAmount().toString());


            //clean up html to render html properly.Will show error if any open tag or any issue.
            HtmlCleaner htmlCleaner = new HtmlCleaner();
            CleanerProperties cleanerProperties = new CleanerProperties();

            // set some properties to non-default values
            cleanerProperties.setTranslateSpecialEntities(true);
            cleanerProperties.setTransResCharsToNCR(true);
            cleanerProperties.setOmitComments(true);

            //tagnode contains clean html code
            TagNode cleanedHtmlNode=htmlCleaner.clean(fileStr);

            FilePath path = new FilePath(getBillerInfo().getId());

            File file = new File(path.getFileUploadDirectory()+path.generateSubFolderPath());
            Boolean folderExists = false;
            if(file.exists()){
                folderExists = true;
            } else{
                folderExists = file.mkdirs();
            }

            if(folderExists){
                String tempFileFullPath = path.getFullPath();
                PrettyXmlSerializer prettyXmlSerializer = new PrettyXmlSerializer(cleanerProperties);
                prettyXmlSerializer.writeToFile(cleanedHtmlNode,tempFileFullPath, StandardCharsets.UTF_8.name());
            }

            WrapperConfig wrapperConfig = new WrapperConfig(WrapperConfig.findExecutable());
            Pdf pdf = new Pdf(wrapperConfig);
            pdf.addPageFromFile(path.getFullPath());
            path.setFileTypes(FileTypes.PDF);
            pdf.saveAs("/home/nishtha/Work/invoice-backend/OuputPdfs/"+path.getSubFolderPath()+path.getFileName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
