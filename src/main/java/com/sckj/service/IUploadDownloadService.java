package com.sckj.service;

import com.sckj.model.model.UploadDownloadModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IUploadDownloadService {
    UploadDownloadModel uploadImage(MultipartFile file, HttpServletRequest request) throws Exception;
}
