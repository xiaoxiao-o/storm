package com.whyxs.controller;

import com.whyxs.common.bean.vo.UploadResultVo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    //项目名,文件的root路径
    @Value("#{configProperties['projectName']}")
    private String projectName;

    //上传的root路径
    @Value("#{configProperties['upload.basePath']}")
    private String uploadBasePath;

    //nginx访问静态文件的root路径
    @Value("#{configProperties['nginx.file.url']}")
    private String nginxFileUrl;

    /**
     * 文件上传(layui,返回值兼容Simditor富文本图片上传)
     */
    @ResponseBody
    @RequestMapping("/upload/{folder}")
    public UploadResultVo fileUpload(@PathVariable String folder, @RequestParam MultipartFile file) throws IOException {
        try {
            if (!file.isEmpty()) {
                //文件上传
                String oName = file.getOriginalFilename();
                String ext = FilenameUtils.getExtension(oName);
                String reName = new Date().getTime() + RandomUtils.nextLong(100000, 999999) + "." + ext;
                String cdate = DateFormatUtils.format(new Date(), "yyyyMMdd");
                String realPath = uploadBasePath + "/" + projectName + "/" + folder + "/" + cdate;
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, reName));
                return UploadResultVo.bulid(0, "上传成功", true, oName, nginxFileUrl + "/" + projectName + "/" + folder + "/" + cdate + "/" + reName);
            }else{
                return UploadResultVo.bulid(1, "上传失败,空文件", true, "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return UploadResultVo.bulid(1, "上传失败", true, "", "");
        }
    }

    /**
     * 文件下载
     */
    @ResponseBody
    @RequestMapping("/download")
    public void download(@RequestParam String filePath, @RequestParam String fileName) throws IOException {
        InputStream input =null;
        OutputStream output = null;
        try {
            URL url = new URL(filePath);
            input = url.openStream();//获取流
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
            output = response.getOutputStream();//获取流
            byte[] bytes = IOUtils.toByteArray(input);//输入流转化为字节
            IOUtils.write(bytes, response.getOutputStream());//写文件
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

}