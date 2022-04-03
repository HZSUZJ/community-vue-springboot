package com.su.community.controller;

import com.alibaba.fastjson.JSON;
import com.su.community.dto.FileDTO;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {
    @ResponseBody
    @RequestMapping("/file/upload")
    public String upload(){
        FileDTO fileDTO=new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/loading.gif");
        return JSON.toJSONString(fileDTO);
    }

}
