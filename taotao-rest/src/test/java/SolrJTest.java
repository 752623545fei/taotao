import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by lyf on 2016/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SolrJTest {

    @Resource
    TbItemMapper itemMapper;

    @Resource
    TbItemDescMapper itemDescMapper;

    @Test
    public void addDocument() throws IOException, SolrServerException {
        HttpSolrServer server = new HttpSolrServer("http://192.168.56.101:8080/solr");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","test001");
        document.addField("item_title","testItem1");
        document.addField("item_price",4654654);
        server.add(document);
        server.commit();

    }

    @Test
    public void delDocument() throws IOException, SolrServerException {
        HttpSolrServer server = new HttpSolrServer("http://192.168.56.101:8080/solr");
        server.deleteById("test001");
        server.deleteByQuery("*:*");
        server.commit();

    }

    @Test
    public void testGetDesc(){







    }

    private String getDesc(String url){
        try {
            Parser parser = new Parser(url);
            parser.setEncoding("GBK");

            NodeFilter filter = new HasAttributeFilter("id","product-detail-1");
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            String s = nodeList.toHtml();
            //System.out.println(s);

            return s;
        } catch (ParserException e) {
            e.printStackTrace();
            return null;
        }
    }
}
