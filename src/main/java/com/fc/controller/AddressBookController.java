package com.fc.controller;

import com.fc.common.BaseContext;
import com.fc.entity.AddressBook;
import com.fc.service.AddressBookService;
import com.fc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址簿管理
 */
@Slf4j
@RestController
@RequestMapping("addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增地址
     */
    @PostMapping
    public ResultVO<AddressBook> save(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}", addressBook);
        if (addressBookService.save(addressBook)) {
            return ResultVO.success(addressBook);
        }

        return ResultVO.error("新增地址失败");
    }

    /**
     * 设置默认地址
     */
    @PutMapping("default")
    public ResultVO<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
        log.info("addressBook:{}", addressBook);

        if (addressBookService.setDefault(addressBook)) {

            return ResultVO.success(addressBook);
        }

        return ResultVO.error("设置默认地址失败");
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public ResultVO<AddressBook> get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        if (addressBook != null) {
            return ResultVO.success(addressBook);
        } else {
            return ResultVO.error("没有找到该地址");
        }
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public ResultVO<AddressBook> getDefault() {
        // 获取指定id的默认地址
        AddressBook addressBook = addressBookService.getDefaultAddressBook(BaseContext.getCurrentId());

        if (null == addressBook) {
            return ResultVO.error("没有找到该地址");
        } else {
            return ResultVO.success(addressBook);
        }
    }

    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("list")
    public ResultVO<List<AddressBook>> list(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        List<AddressBook> addressBooks = addressBookService.findAll(addressBook.getUserId());

        return ResultVO.success(addressBooks);
    }
}
