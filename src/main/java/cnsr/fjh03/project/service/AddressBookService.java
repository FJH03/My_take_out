package cnsr.fjh03.project.service;

import cnsr.fjh03.project.pojo.AddressBook;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: AddressBookService
 * @Date: 2025/2/14
 * @Time: 13:03
 * @Description:添加自定义描述
 */
public interface AddressBookService extends IService<AddressBook> {
    AddressBook setDefault(AddressBook addressBook);

    AddressBook getDefault();

    AddressBook mysave(AddressBook addressBook);

    List<AddressBook> list(AddressBook addressBook);
}
