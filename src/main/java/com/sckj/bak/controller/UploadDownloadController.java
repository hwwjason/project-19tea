package com.sckj.bak.controller;
 
import com.alibaba.fastjson.JSONObject;
import com.sckj.common.ResultData;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.model.model.UploadDownloadModel;
import com.sckj.service.IUploadDownloadService;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.FileUtils;
import com.sckj.utils.HttpUtils;
import com.sckj.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 2017/10/30.
 */
@RestController
@RequestMapping("bak/image")
public class UploadDownloadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadDownloadController.class);

    @Value("${uploadDir}")
    private String uploadDir;

    @Autowired
    private IUploadDownloadService uploadDownloadService;
 
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ResultData uploadImage(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request) throws Exception {
        ResultData resultData = new ResultData();
        try{
            if (file==null || file.isEmpty()) {
                logger.info("图片不能为空！！！");
                resultData.setMessage("图片不能为空");
                resultData.setStatus(ResultStatusEnum.FAIL.toString());
                return resultData;
            }
            UploadDownloadModel uploadDownloadModel = uploadDownloadService.uploadImage(file,request);
            resultData.setPath(uploadDownloadModel.getUrl());
        }catch (Exception e){
            resultData.setMessage("文件上传失败");
            resultData.setStatus(ResultStatusEnum.FAIL.toString());
        }
        return resultData;
    }

    @RequestMapping(value="showImg")
    public void showImg(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String imgFile = request.getParameter("imgFile"); //文件名
        //String path= "/Users/hww/Documents/IMAGE/";//这里是存放图片的文件夹地址
        String path= uploadDir;//这里是存放图片的文件夹地址
        FileInputStream fileIs=null;
        try {
            fileIs = new FileInputStream(path+"/"+imgFile);
        } catch (Exception e) {
            logger.error("系统找不到图像文件："+path+"/"+imgFile);
            return;
        }
        int i=fileIs.available(); //得到文件大小
        byte data[]=new byte[i];
        fileIs.read(data);  //读数据
        response.setContentType("image/*"); //设置返回的文件类型
        OutputStream outStream=response.getOutputStream(); //得到向客户端输出二进制数据的对象
        outStream.write(data);  //输出数据
        outStream.flush();
        outStream.close();
        fileIs.close();
    }


    //文件下载相关代码
    @RequestMapping(value = "/downloadImage",method = RequestMethod.GET)
    public String downloadImage(String imageName,HttpServletRequest request, HttpServletResponse response) {
        logger.debug("the imageName is : "+imageName);
        String fileUrl = uploadDir+imageName;
        if (fileUrl != null) {
            File file = new File(fileUrl);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + imageName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + i + " because the file was empty.";
            }
        }
        return "upload successful";
    }
 
 
}
