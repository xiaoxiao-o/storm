package com.whyxs.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.whyxs.common.bean.vo.RestResultVo;

@Controller
public class FileController extends BaseController {
	
	@Value("${upload.basePath}")
	private String uploadBasePath;

	/**
	 * 上传文件
	 */
	@ResponseBody
	@RequestMapping("/file/upload/{folder}")
	public RestResultVo fileUpload(@PathVariable String folder, @RequestParam MultipartFile[] files) throws IOException{
		try {
			List<String> urls = new ArrayList<String>();
			for(MultipartFile file : files){
				if (!file.isEmpty()) {
					String ext =  FilenameUtils.getExtension(file.getOriginalFilename());
		            String reName = RandomStringUtils.randomAlphanumeric(32).toLowerCase() + "."+ ext;
		            String cdate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		            String realPath = uploadBasePath + File.separator + folder+ File.separator +cdate; 
		            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, reName)); 
		            urls.add("/"+folder+cdate+"/"+reName);
				}else {
					urls.add("");
				}
			}
			return RestResultVo.success(urls);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}  
	}
	
	/**
	 * 文件下载,待补充
	 */
}
