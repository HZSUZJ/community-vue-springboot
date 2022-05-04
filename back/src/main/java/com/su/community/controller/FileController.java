//package com.su.community.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.ClientConfig;
//import com.qcloud.cos.auth.BasicCOSCredentials;
//import com.qcloud.cos.auth.COSCredentials;
//import com.qcloud.cos.exception.CosClientException;
//import com.qcloud.cos.exception.CosServiceException;
//import com.qcloud.cos.http.HttpProtocol;
//import com.qcloud.cos.model.*;
//import com.qcloud.cos.region.Region;
//import com.su.community.dto.FileDTO;
//import com.su.community.provider.TXCloudProvider;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.io.IOException;
//
//@Controller
//public class FileController {
//
//    @Autowired
//    private TXCloudProvider txCloudProvider;
//    @ResponseBody
//    @RequestMapping("/file/upload")
//    public String upload(HttpServletRequest request) throws IOException {
//        MultipartHttpServletRequest multipartHttpServletRequest= (MultipartHttpServletRequest) request;
//        MultipartFile file=multipartHttpServletRequest.getFile("editormd-image-file");
//        String url= txCloudProvider.upload(file.getInputStream(),file.getOriginalFilename(),file.getSize());
//        FileDTO fileDTO=new FileDTO();
//        fileDTO.setSuccess(1);
//        fileDTO.setUrl(url);
//        return JSON.toJSONString(fileDTO);
//    }
//
//}
