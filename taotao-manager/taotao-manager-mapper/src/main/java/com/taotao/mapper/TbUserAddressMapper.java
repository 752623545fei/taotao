package com.taotao.mapper;

import com.taotao.pojo.TbUserAddress;
import com.taotao.pojo.TbUserAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbUserAddressMapper {
    int countByExample(TbUserAddressExample example);

    int deleteByExample(TbUserAddressExample example);

    int deleteByPrimaryKey(Integer addressId);

    int insert(TbUserAddress record);

    int insertSelective(TbUserAddress record);

    List<TbUserAddress> selectByExample(TbUserAddressExample example);

    TbUserAddress selectByPrimaryKey(Integer addressId);

    int updateByExampleSelective(@Param("record") TbUserAddress record, @Param("example") TbUserAddressExample example);

    int updateByExample(@Param("record") TbUserAddress record, @Param("example") TbUserAddressExample example);

    int updateByPrimaryKeySelective(TbUserAddress record);

    int updateByPrimaryKey(TbUserAddress record);
}