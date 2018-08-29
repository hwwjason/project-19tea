package com.sckj.bak.controller;
 
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sckj.common.ResultData;
import com.sckj.exception.BusinessException;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.FileUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
 
//import com.wm.springboot.modelUtils.GetKey;
//import com.wm.springboot.modelUtils.RespResult;
//import com.wm.springboot.modelUtils.RespResultEnum;
//import com.wm.springboot.modelUtils.RespResultUtil;
//import com.wm.springboot.sc.entity.User;
 
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件上传工具类
 * 支持多文件上传
 * @author maybe
 */
@Slf4j
@RestController
@RequestMapping("bak/image_")
public class FileUpload {

	private static final Logger logger = LoggerFactory.getLogger(UploadDownloadController.class);
	@Value("${uploadDir}")
	private String uploadDir;

	/**
	 * （多）文件上传入口
	 * @param files 文件数组
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	ResultData fileUpload(@RequestParam("file")MultipartFile[] files, HttpServletRequest request) {
		List<MultipartFile> fileList = new ArrayList<>();
		for (MultipartFile file : files) {
			fileList.add(file);
		}
		return FileUtils.upload(fileList,request);
	}

//	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
//	ResultData  imgUpLoad(List<MultipartFile> files) {
//		System.out.println("pppp");
//		return null;
//	}

	@RequestMapping("/uploadImage_")
	public void upload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest Murequest, Integer empId) {
 		System.out.println("开始接受文件");
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
	}
	
	

 
	/**
	 * 一次上传访问，取一次上传路径
	 * @param request
	 * @return
	 */
//	private String getPath(HttpServletRequest request) {
//		String path = "";
//		@SuppressWarnings("unchecked")
//		String rootPath = ((Map<String, String>) request.getServletContext().getAttribute(GetKey.configMap.toString()))
//				.get(GetKey.uploadpath.toString()); // 取根目录
//		if (null == rootPath || "".equals(rootPath)) throw new NullPointerException(RespResultEnum.ROOTPATHISNULL.getMsg());
//		User user = (User) request.getSession().getAttribute(GetKey.user.toString()); // 取用户名，做中间目录
//		if (user != null) path = rootPath + user + "\\";
//		else path = rootPath + "other\\";
//		File file2 = new File(path);
//		if (!file2.exists()) file2.mkdirs();
//		log.info("文件存储路径path:{}", path);
//		return path;
//	}
}
