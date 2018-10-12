package com.sckj.service.imp;

import com.sckj.bak.controller.UploadDownloadController;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.model.model.UploadDownloadModel;
import com.sckj.service.IUploadDownloadService;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.HttpUtils;
import com.sckj.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class UploadDownloadServiceImp implements IUploadDownloadService{

    private static final Logger logger = LoggerFactory.getLogger(UploadDownloadController.class);
    @Value("${uploadDir}")
    private String uploadDir;

    @Override
    public UploadDownloadModel uploadImage(MultipartFile file, HttpServletRequest request) throws Exception {
        if (file==null || file.isEmpty()) {
            logger.info("图片不能为空！！！");
            return null;
        }
        UploadDownloadModel uploadDownloadModel = new UploadDownloadModel();

        logger.info("开始上传图片===>>>");
        logger.info("图片固定路劲前缀:"+uploadDir);

        String originalFileName = file.getOriginalFilename();// 获取文件名
        String suffixName = originalFileName.substring(originalFileName.lastIndexOf(".")); // 获取文件的后缀名
        uploadDownloadModel.setOriginFileName(originalFileName);

        String fileName = DateTimeUtils.getCurDate2() + "/" + UUIDUtils.generate()+suffixName;// 解决中文问题，liunx下中文路径，图片显示问题
        uploadDownloadModel.setFileName(fileName);
        String filePath = uploadDir + fileName;
        File dest = new File(filePath);

        uploadDownloadModel.setFilePath(filePath);
        logger.info("图片的物理径为:"+filePath );

        if (!dest.getParentFile().exists()) {// 检测是否存在目录,不存在创建目录
            dest.getParentFile().mkdirs();
        }

        String url = "";
        try {
            file.transferTo(dest);// ???
            if(HttpUtils.getEn0().equals("172.19.60.150")){
                url = "https://sowtea.com:2433/bak/image/showImg?imgFile=" + fileName;
            }else{
                url = "https://"+HttpUtils.getEn0()+"/bak/image/showImg?imgFile=" +fileName;
            }
            uploadDownloadModel.setUrlWithoutIp("bak/image/showImg?imgFile=" + fileName);
            uploadDownloadModel.setUrl(url);
            logger.info("图片访问路径:"+url);
            return uploadDownloadModel;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
