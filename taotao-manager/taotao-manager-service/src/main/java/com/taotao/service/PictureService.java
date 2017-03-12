package com.taotao.service;

import com.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by lyf on 2016/12/5.
 */
public interface PictureService {
    PictureResult uploadPicture(MultipartFile uploadFile);
}
