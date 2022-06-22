package com.fc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookDao extends BaseMapper<AddressBook> {

}
