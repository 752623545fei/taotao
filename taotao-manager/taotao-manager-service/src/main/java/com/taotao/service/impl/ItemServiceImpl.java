package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.DateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {


	@Value("${SEARCH_BASE_URL}")
	String SEARCH_BASE_URL;

	@Value("${SEARCH_ADD_URL}")
	String SEARCH_ADD_URL;

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	TbItemDescMapper descMapper;

	@Autowired
	TbItemParamItemMapper paramItemMapper;


	@Override
	public TbItem queryItem(long id) {
		TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		return tbItem;
	}

	@Override
	public DateGridResult queryItemByPage(Integer page, Integer rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page,rows);

		List<TbItem> list = itemMapper.selectByExample(example);
		DateGridResult result = new DateGridResult();
		PageInfo<TbItem> info = new PageInfo<>(list);
		result.setTotal(info.getTotal());
		result.setRows(list);
		return result;
	}


	@Override
	public TaotaoResult addItem(TbItem tbItem, String desc,String param) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		Long itemId;
		try {
			itemId = IDUtils.genItemId();

			tbItem.setId(itemId);
			tbItem.setStatus((byte) 1);
			Date date = new Date();
			tbItem.setCreated(date);
			tbItem.setUpdated(date);

			itemMapper.insert(tbItem);
			itemDesc.setItemId(itemId);
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			descMapper.insert(itemDesc);

			TaotaoResult result = this.insertItemParam(itemId,param);
		}catch (Exception e){
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		String s = HttpClientUtil.doGet(SEARCH_BASE_URL + SEARCH_ADD_URL + itemId);
		return TaotaoResult.ok();
	}


	private TaotaoResult insertItemParam(Long itemId, String itemParam){
		TbItemParamItem param = new TbItemParamItem();
		param.setCreated(new Date());
		param.setUpdated(new Date());
		param.setParamData(itemParam);
		param.setItemId(itemId);
		paramItemMapper.insert(param);
		return TaotaoResult.ok();
	}
}
