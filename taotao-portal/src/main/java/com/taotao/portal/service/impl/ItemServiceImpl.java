package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lyf on 2016/12/11.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    String REST_BASE_URL;

    @Value("${ITEM_INFO_URL}")
    String ITEM_INFO_URL;

    @Value("${ITEM_DESC_URL}")
    String ITEM_DESC_URL;

    @Value("${ITEM_PARAM_URL}")
    String ITEM_PARAM_URL;

    @Override
    public ItemInfo getItemById(long itemId) {
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);

        try {
            if (!StringUtils.isBlank(json)) {
                TaotaoResult result = TaotaoResult.formatToPojo(json, ItemInfo.class);
                if (result.getStatus() == 200){
                    ItemInfo item = (ItemInfo) result.getData();
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
        try {
            if (!StringUtils.isBlank(json)) {
                TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemDesc.class);
                if (result.getStatus() == 200){
                    TbItemDesc item = (TbItemDesc) result.getData();
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParamById(long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            //把json转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
            if (taotaoResult.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
                String paramData = itemParamItem.getParamData();
                //生成html
                // 把规格参数json数据转换成java对象
                List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("    <tbody>\n");
                for(Map m1:jsonList) {
                    sb.append("        <tr>\n");
                    sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
                    sb.append("        </tr>\n");
                    List<Map> list2 = (List<Map>) m1.get("params");
                    for(Map m2:list2) {
                        sb.append("        <tr>\n");
                        sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                        sb.append("            <td>"+m2.get("v")+"</td>\n");
                        sb.append("        </tr>\n");
                    }
                }
                sb.append("    </tbody>\n");
                sb.append("</table>");
                //返回html片段
                return sb.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }
}
