package com.taotao.controller;

import com.taotao.common.pojo.DateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

	@Autowired
	private ItemService itemService;



	@RequestMapping(value="/{itemId}")
	@ResponseBody
	public TbItem getItem(@PathVariable("itemId") long id){
		TbItem item = itemService.queryItem(id);
		return item;
	}


	@RequestMapping(value="/list")
	@ResponseBody
	public DateGridResult getItemByPage(@Param("page") Integer page , @Param("rows") Integer rows){
		DateGridResult result = itemService.queryItemByPage(page,rows);
		return result;
	}


	@RequestMapping(value = "save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item, String desc,String itemParams){

		TaotaoResult result = itemService.addItem(item,desc,itemParams);
		return result;
	}
}
