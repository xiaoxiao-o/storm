package com.whyxs.controller;

import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.util.UUIDUtil;
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

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UploadController extends BaseController {

	@Value("#{configProperties['projectName']}")
	private String projectName;

	@Value("#{configProperties['upload.basePath']}")
	private String uploadBasePath;

	@Value("#{configProperties['nginx.file.url']}")
	private String nginxFileUrl;


	/**
	 * 上传文件(批量)
	 */
	@ResponseBody
	@RequestMapping("/file/upload/{folder}")
	public RestResultVo fileUpload(@PathVariable String folder, @RequestParam MultipartFile[] file) throws IOException{
		try {
			List<String> urls = new ArrayList<String>();
			for(MultipartFile sfile : file){
				if (!sfile.isEmpty()) {
					String oName = sfile.getOriginalFilename();
					String ext =  FilenameUtils.getExtension(oName);
					String reName = RandomStringUtils.randomAlphanumeric(32).toLowerCase() + "."+ ext;
					String cdate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
					String realPath = uploadBasePath+"/"+projectName+"/"+folder+"/"+cdate;
					FileUtils.copyInputStreamToFile(sfile.getInputStream(), new File(realPath, reName));
					urls.add(nginxFileUrl+"/"+projectName+"/"+folder+"/"+cdate+"/"+reName);
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
	 * 上传图片(单个图片)
	 */
	@ResponseBody
	@RequestMapping("/img/upload/{folder}")
	public RestResultVo fileUpload(@PathVariable String folder, @RequestParam MultipartFile file) throws IOException{
		try {
			Map<String,String> data = new HashMap<>();
			if (!file.isEmpty()) {
				String oName = file.getOriginalFilename();
				String ext =  FilenameUtils.getExtension(oName);
				String reName = UUIDUtil.get32UUID()+"."+ ext;
				String cdate = DateFormatUtils.format(new Date(), "yyyyMMdd");
				String realPath = uploadBasePath+"/"+projectName+"/"+folder+"/"+cdate;
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, reName));
				data.put("src",nginxFileUrl+"/"+projectName+"/"+folder+"/"+cdate+"/"+reName);
				data.put("title",oName);
			}
			return RestResultVo.build(0,"上传成功",data);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.build(-1,"上传失败",null);
		}
	}
	
}
