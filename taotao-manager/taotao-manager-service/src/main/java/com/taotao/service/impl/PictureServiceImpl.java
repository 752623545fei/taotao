package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.util.FtpUtil;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by lyf on 2016/12/5.
 */

@Service
public class PictureServiceImpl implements PictureService{

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USER_NAME}")
    private String FTP_USER_NAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;



    @Override
    public PictureResult uploadPicture(MultipartFile uploadFile) {
        if (null == uploadFile || uploadFile.isEmpty()) {
            return PictureResult.error("上传图片为空");
        }


        String oldName = uploadFile.getOriginalFilename();
        String ext = oldName.substring(oldName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String imageName = uuid.toString();

        DateTime dateTime = new DateTime();
        String filePath = dateTime.toString("/yyyy/MM/dd");


        try {
            FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER_NAME, FTP_PASSWORD,
                    FTP_BASE_PATH, filePath, imageName + ext, uploadFile.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return PictureResult.ok(IMAGE_BASE_URL + filePath + "/" + imageName + ext);
    }
}
