import com.taotao.common.util.HttpClientUtil;
import org.junit.Test;

/**
 * Created by lyf on 2016/12/13.
 */
public class HttpclientTest {


    @Test
    public void test1(){
        String s = HttpClientUtil.doGet("https://item.jd.com/10040257180.html");
        System.out.println(s);
    }
}
