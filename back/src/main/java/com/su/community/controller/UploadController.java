package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@RestController
public class UploadController {


    @PostMapping("/uploadFile")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String originName=file.getOriginalFilename();
        //判断类型
        String realPath=ResourceUtils.getURL("classpath:").getPath().replaceAll("%20"," ") +"static/upload/";
        String newName=UUID.randomUUID().toString()+originName;
        String filePath = realPath + newName;
        File folder=new File(realPath);
        if (!folder.exists()){
            folder.mkdirs();
        }
        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        String url=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/upload/"+newName;
        JSONObject jsonObject = new JSONObject();
        HashMap<String, String> map = new HashMap<>();
        map.put("code","200");
        map.put("url",url);
        jsonObject.put("data",map);
        return jsonObject.toJSONString();
    }


}
