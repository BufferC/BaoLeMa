package com.fc.service;

import com.fc.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    boolean setDefault(AddressBook addressBook);

    boolean save(AddressBook addressBook);

    AddressBook getById(Long id);

    AddressBook getDefaultAddressBook(Long currentId);

    List<AddressBook> findAll(Long userId);
}
