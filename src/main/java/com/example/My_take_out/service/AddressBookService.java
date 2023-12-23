package com.example.My_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.My_take_out.pojo.AddressBook;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: AddressBookService
 * @Date: 2023/12/21
 * @Time: 9:03
 * @Description:添加自定义描述
 */
public interface AddressBookService extends IService<AddressBook> {
    AddressBook setDefault(AddressBook addressBook);

    AddressBook getDefault();

    AddressBook mysave(AddressBook addressBook);

    List<AddressBook> list(AddressBook addressBook);
}
