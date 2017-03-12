import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * Created by lyf on 2016/12/26.
 */
public class SolrCloudTest {

    @Test
    public void testAdd() throws Exception{
        String zkHost = "192.168.56.101:2181,192.168.56.101:2182,192.168.56.101:2183";
        CloudSolrServer solrServer = new CloudSolrServer(zkHost);

        solrServer.setDefaultCollection("collection2");

        solrServer.setDefaultCollection("collection2");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加域
        document.addField("id", "test001");
        document.addField("item_title", "测试商品");
        //把文档添加到索引库
        solrServer.add(document);
        //提交
        solrServer.commit();

    }
}
