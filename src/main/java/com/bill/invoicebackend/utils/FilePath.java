package com.bill.invoicebackend.utils;

import com.bill.invoicebackend.utils.enums.FileTypes;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FilePath {

    private String userId;
    private String subFolderPath;
    private String uuid;
    private FileTypes fileTypes;
    private String fileUploadDirectory;

    public FilePath(String userId){
        this.userId = userId;
        this.subFolderPath = generateSubFolderPath();
        this.uuid = UUID.randomUUID().toString();
        this.fileTypes = FileTypes.HTML;
        this.fileUploadDirectory = "/home/nishtha/Work/invoice-backend/FileUpload/";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubFolderPath() {
        return subFolderPath;
    }

    public void setSubFolderPath(String subFolderPath) {
        this.subFolderPath = subFolderPath;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public FileTypes getFileTypes() {
        return fileTypes;
    }

    public void setFileTypes(FileTypes fileTypes) {
        this.fileTypes = fileTypes;
    }

    public String getFileUploadDirectory() {
        return fileUploadDirectory;
    }

    public void setFileUploadDirectory(String fileUploadDirectory) {
        this.fileUploadDirectory = fileUploadDirectory;
    }

    public String generateDateStructure(){
        ZonedDateTime now = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
        return now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
    public String generateSubFolderPath(){
        return this.userId+ File.separator+ generateDateStructure();
    }

    public String getPartialPath(){
        return generateSubFolderPath()+getFileName();
    }

    public String getFileName(){
        return File.separator+getUuid()+"."+getFileTypes().getSmall();
    }

    public String getFullPath(){
        return getFileUploadDirectory()+ generateSubFolderPath()+ getFileName();
    }

}
