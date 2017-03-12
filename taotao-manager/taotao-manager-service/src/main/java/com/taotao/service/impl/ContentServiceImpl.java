package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.DateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lyf on 2016/12/7.
 */

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper contentMapper;

    @Value("${REST_BASE_URL}")
    String REST_BASE_URL;

    @Value("${REST_CONTENT_SYNC_URL}")
    String REST_CONTENT_SYNC_URL;

    @Override
    public DateGridResult queryContentByPage(long categoryId, int page, int rows) {
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page,rows);
        List<TbContent> tbContents = contentMapper.selectByExample(example);
        DateGridResult result = new DateGridResult();
        PageInfo<TbContent> info = new PageInfo<>(tbContents);
        result.setRows(tbContents);
        result.setTotal(info.getTotal());
        return result;
    }

    @Override
    public TaotaoResult insertContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        contentMapper.insert(tbContent);

        long cid = tbContent.getCategoryId();
        try {
            String s = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + cid);
        }catch (Exception e){
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContent(String ids) {
        String[] id = ids.split(",");
        for (int i=0; i<id.length; i++){
            Long contentId = Long.valueOf(id[i]);
            TbContent content = contentMapper.selectByPrimaryKey(contentId);
            Long categoryId = content.getCategoryId();
            contentMapper.deleteByPrimaryKey(contentId);
            try {
                HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + categoryId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TaotaoResult.ok();
    }
}
