package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fc.common.BaseContext;
import com.fc.dao.AddressBookDao;
import com.fc.entity.AddressBook;
import com.fc.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Autowired
    private AddressBookDao addressBookDao;

    @Override
    public boolean setDefault(AddressBook addressBook) {
        // 把原本的默认地址给移除掉
        LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
        wrapper.eq(AddressBook::getIsDefault, 1);
        wrapper.set(AddressBook::getIsDefault, 0);
        addressBookDao.update(null, wrapper);

        // 设置新的默认地址
        addressBook.setIsDefault(1);
        return addressBookDao.updateById(addressBook) > 0;
    }

    @Override
    public boolean save(AddressBook addressBook) {
        return addressBookDao.insert(addressBook) > 0;
    }

    @Override
    public AddressBook getById(Long id) {
        return addressBookDao.selectById(id);
    }

    @Override
    public AddressBook getDefaultAddressBook(Long currentId) {
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, currentId);
        queryWrapper.eq(AddressBook::getIsDefault, 1);
        return addressBookDao.selectOne(queryWrapper);
    }

    @Override
    public List<AddressBook> findAll(Long userId) {
        //条件构造器
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, userId);
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);
        return addressBookDao.selectList(queryWrapper);
    }
}
