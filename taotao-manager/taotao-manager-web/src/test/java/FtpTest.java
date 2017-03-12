import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by lyf on 2016/12/4.
 */
public class FtpTest {

    @Test
    public void testFtp() throws IOException {
        FTPClient client = new FTPClient();
        client.connect("192.168.56.102",21);
        client.login("ftpuser","123");
        FileInputStream inputStream = new FileInputStream(new File("H:\\迅雷下载\\EZD-311\\EZD-311.jpg"));
        client.changeWorkingDirectory("/home/ftpuser/www/images/2016/12/05");
        client.setFileType(FTPClient.BINARY_FILE_TYPE);
        client.storeFile("hello.jpg", inputStream);
        client.logout();
    }
}
