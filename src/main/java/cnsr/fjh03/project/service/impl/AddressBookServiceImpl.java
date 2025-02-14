package cnsr.fjh03.project.service.impl;

import cnsr.fjh03.project.mapper.AddressBookMapper;
import cnsr.fjh03.project.pojo.AddressBook;
import cnsr.fjh03.project.service.AddressBookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: AddressBookServiceImpl
 * @Date: 2025/2/14
 * @Time: 9:07
 * @Description:添加自定义描述
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

    @Autowired
    HttpSession session;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AddressBook setDefault(AddressBook addressBook) {
        LambdaUpdateWrapper lambdaUpdateWrapper = new LambdaUpdateWrapper<AddressBook>()
                .eq(AddressBook::getUserId, Long.parseLong(session.getAttribute("user").toString()))
                .set(AddressBook::getIsDefault, 0);

        this.update(lambdaUpdateWrapper);

        addressBook.setIsDefault(1);

        this.updateById(addressBook);
        return addressBook;
    }

    @Override
    public AddressBook getDefault() {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<AddressBook>()
                .eq(AddressBook::getUserId, Long.parseLong(session.getAttribute("user").toString()))
                .eq(AddressBook::getIsDefault, 1);

        AddressBook addressBook = this.getOne(lambdaQueryWrapper);

        return addressBook;
    }

    @Override
    public AddressBook mysave(AddressBook addressBook) {
        addressBook.setUserId(Long.parseLong(session.getAttribute("user").toString()));
        this.save(addressBook);
        return addressBook;
    }

    @Override
    public List<AddressBook> list(AddressBook addressBook) {
        addressBook.setUserId(Long.parseLong(session.getAttribute("user").toString()));

        //条件构造器
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(addressBook.getUserId() != null, AddressBook::getUserId, addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);

        //SQL:select * from address_book where user_id = ? order by update_time desc
        return this.list(queryWrapper);
    }
}
