import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.mapper.NewItemMapper;
import com.taotao.search.pojo.Item;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyf on 2016/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-solr.xml"})
public class  SolrJTest {

    @Resource
    TbItemMapper tbItemMapper;

    @Resource
    ItemMapper itemMapper;

    @Resource
    NewItemMapper newItemMapper;

    @Test
    public void testQuery() throws SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.56.101:8080/solr");
        //创建一个查询对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery("*:*");
        query.setStart(20);
        query.setRows(50);
        //执行查询
        QueryResponse response = solrServer.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("共查询到记录：" + solrDocumentList.getNumFound());
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));

        }

    }

    @Test
    public void testItem(){
        long key = 1217755l;
        int count = 0;
        String last = "";
        String temp = "";
        TbItem item = null;
        while (key < 2300143){
            item = tbItemMapper.selectByPrimaryKey(key);
            try {
                temp = item.getTitle().substring(0,8);

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (temp.equals(last)){
                count++;
                System.out.println(item.getId());
                tbItemMapper.deleteByPrimaryKey(item.getId());
                last = temp;
                key++;
            }

        }

        System.out.println(count);
    }

    @Test
    public void testQuery2(){
        String last = "";
        String temp = "";
        int count = 0;
        try {
            for (long id = 1217755;id < 2300143;id++){
                TbItem item = tbItemMapper.selectByPrimaryKey(id);
                temp = item.getTitle().substring(0,8);
                if (temp.equals(last)){
                    count++;
                    System.out.println(item.getId());
                    //tbItemMapper.deleteByPrimaryKey(item.getId());
                    last = temp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }

    @Test
    public void test2(){
        List<Item> itemList = itemMapper.getItemList();
        System.out.println(itemList.size());
        String last = "";
        String temp = "";
        int count = 0;


        for (Item item : itemList){

            if (item.getTitle().length()<10){
                temp = item.getTitle().substring(0,item.getTitle().length());
            }else {
                temp = item.getTitle().substring(0,10);
            }
            //System.out.println(temp);
            if (temp.equals(last)){
                count++;
                System.out.println(item.getId());
                tbItemMapper.deleteByPrimaryKey(Long.valueOf(item.getId()));

            }
            last = temp;
        }

        System.out.println(count);
    }


    @Test
    public void testPrice(){
        List<TbItem> items = newItemMapper.getPriceById();
        for (TbItem item : items){
            try {
                if (item.getPrice()!=null && item.getPrice()!=0){
                    newItemMapper.updatePrice(item.getId(), item.getPrice());
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    @Test
    public void testUpdate(){
        long id = 1217755L;
        long price = 12900L;
        newItemMapper.updatePrice(id, price);
    }
}
