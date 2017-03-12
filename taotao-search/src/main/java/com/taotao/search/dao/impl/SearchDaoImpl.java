package com.taotao.search.dao.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by lyf on 2016/12/10.
 */

@Repository
public class SearchDaoImpl implements SearchDao {


    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        SearchResult result = new SearchResult();
        QueryResponse response = solrServer.query(query);
        SolrDocumentList documentList = response.getResults();
        result.setRecordCount(documentList.getNumFound());
        List<Item> itemList = new ArrayList<>();
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        for (SolrDocument solrDocuments : documentList){
            Item item = new Item();
            item.setId((String) solrDocuments.get("id"));
            List<String> list = highlighting.get(solrDocuments.get("id")).get("item_title");
            String title  = "";
            if (list!=null && list.size()>0){
                title = list.get(0);
            }else {
                title = (String) solrDocuments.get("item_title");
            }
            item.setTitle(title);
            item.setImage((String) solrDocuments.get("item_image"));
            item.setPrice((long) solrDocuments.get("item_price"));
            item.setSell_point((String) solrDocuments.get("item_sell_point"));
            item.setCategory_name((String) solrDocuments.get("item_category_name"));
            //添加的商品列表
            itemList.add(item);
        }
        result.setItemList(itemList);
        return result;
    }
}
