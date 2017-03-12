package com.taotao.search.mapper;

import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lyf on 2016/12/14.
 */
public interface NewItemMapper {

    List<TbItem> getPriceById();

    int updatePrice(@Param("id") Long id, @Param("price") Long price);
}
