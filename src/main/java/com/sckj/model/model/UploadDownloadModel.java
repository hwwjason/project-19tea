package com.sckj.model.model;

public class UploadDownloadModel {
    private String originFileName;
    private String fileName;
    private String filePath;
    private String urlWithoutIp;
    private String url;

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlWithoutIp() {
        return urlWithoutIp;
    }

    public void setUrlWithoutIp(String urlWithoutIp) {
        this.urlWithoutIp = urlWithoutIp;
    }
}
