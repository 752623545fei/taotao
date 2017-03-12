package impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.util.FtpUtil;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemService;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by lyf on 2016/11/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class ItemServiceImplTest {

    @Resource
    ItemService itemService;

    @Resource
    PictureService pictureService;

    @Resource
    ItemParamItemService paramItemService;

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

    @Test
    public void queryItem() throws Exception {

    }

    @Test
    public void testFTP() throws FileNotFoundException {

        DateTime dateTime = new DateTime();
        String filePath = dateTime.toString("/yyyy/MM/dd");
        FileInputStream inputStream = new FileInputStream(new File("H:\\迅雷下载\\EZD-311\\EZD-311.jpg"));
        FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER_NAME, FTP_PASSWORD,
                FTP_BASE_PATH, filePath, "123.jpg", inputStream);



    }

    @Test
    public void testParam(){
        String result = paramItemService.getItemParamByItemId(148095709295350L);
        System.out.println(result);
    }


}