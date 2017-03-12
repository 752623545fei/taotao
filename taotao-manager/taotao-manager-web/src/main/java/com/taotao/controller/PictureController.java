package com.taotao.controller;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lyf on 2016/12/5.
 */
@Controller
public class PictureController {

    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "pic/upload")
    @ResponseBody
    public PictureResult uploadPicture(MultipartFile uploadFile){
        PictureResult result = pictureService.uploadPicture(uploadFile);
        return result;
    }
}
