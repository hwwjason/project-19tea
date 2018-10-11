package com.sckj.utils;

import com.sckj.common.ResultData;
import com.sckj.controller.ContentController;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class FileUtils {

    @Value("${uploadDir}")
    private static String uploadDir;

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

//    public static ResultData uploadImage(MultipartFile file,HttpServletRequest request) throws RuntimeException {
//        return null;
//    }


    public static ResultData upload(  Map<String, MultipartFile> files,HttpServletRequest request) {
        List<MultipartFile> fileList = new ArrayList<>();
        for(MultipartFile file : files.values()){
            fileList.add(file);
        }
        return  upload(fileList,request);
    }


    /**
     * 上传操作,支持多文件上传
     * @param files 文件数组
     * @param request
     */
    public static ResultData upload(List<MultipartFile> files, HttpServletRequest request) {
        ResultData resultData = new ResultData();
        log.info("IP:{},进行文件上传——开始",request.getRemoteAddr());
        int count = 0; //文件数统计
        String fileNames = "";
        for(int i=0;i<files.size();i++) {
            if (!files.get(i).isEmpty()) {
                try {
                    String filePath = uploadDir+"/";
                    String originalFilename = files.get(i).getOriginalFilename();
                    String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
                    String fileName  = DateTimeUtils.getCurDate2()+"/"+DateTimeUtils.getCurTime2() + UUIDUtils.generate()+suffixName;
                    log.info("第{}个文件开始上传",i+1);
                    // 使用UUID确保上传文件不重复
//					BufferedOutputStream out = new BufferedOutputStream(
//							new FileOutputStream(new File(getPath(request) + UUID.randomUUID() + "-" +files[i].getOriginalFilename())));
                    File dest = new File(filePath+fileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(dest));
                    if(StringUtils.isEmpty(fileNames)){
                        fileNames = fileName;
                    }else{
                        fileNames = fileNames + "," +fileName;
                    }
                    out.write(files.get(i).getBytes());
                    out.flush();
                    out.close();
                    log.info("第{}个文件上传成功",i+1);
                    count++;
                } catch (FileNotFoundException e) {
                    log.error("文件找不到"+e.getMessage());
                    throw new BusinessException("文件找不到");
                } catch (IOException e) {
                    log.error("IP:{},{}",request.getRemoteAddr(),"文件保存失败"+e.getMessage());
                    throw new BusinessException("文件保存失败");
                } catch (NullPointerException e) {
                    log.error("IP:{},上传文件时获取根路径失败!",request.getRemoteAddr());
                    throw new BusinessException("上传文件时获取根路径失败");
                }catch (Exception e){
                    log.error("IP:{},文件上传失败!",request.getRemoteAddr());
                    throw new BusinessException("文件上传失败");
                }
            }
        }
        if(0==count) throw new BusinessException("文件为空");
        log.info("IP:{},共{}个文件成功上传——结束",request.getRemoteAddr(),count);
        resultData.setPath(fileNames);
        return resultData;
    }

    public static ResultData uploadImage(MultipartFile file,HttpServletRequest request) throws RuntimeException {
        ResultData resultData = new ResultData();
        if (file==null || file.isEmpty()) {
            resultData.setMessage("文件不能为空");
            resultData.setStatus(ResultStatusEnum.FAIL.toString());
            return resultData;
        }
        String path = request.getSession().getServletContext().getRealPath("./image");
        // 获取文件名
        String fileName = file.getOriginalFilename();
        //logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = path+"/";
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = DateTimeUtils.getCurDate2()+"/"+DateTimeUtils.getCurTime2() + UUIDUtils.generate()+suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            if(HttpUtils.getEn0().equals("172.19.60.150")){
                fileName = "https://sowtea.com:2433/image/"+fileName;
            }else{
                fileName = "https://"+HttpUtils.getEn0()+"/image/"+fileName;
            }
            resultData.setPath(fileName);
            resultData.setMessage("文件"+fileName+"上传成功");
            return resultData;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData.setPath(fileName);
        resultData.setMessage("文件上传失败");
        resultData.setStatus(ResultStatusEnum.FAIL.toString());
        return resultData;
    }
}
