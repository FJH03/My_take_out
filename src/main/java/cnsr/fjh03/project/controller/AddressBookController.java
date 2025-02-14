package cnsr.fjh03.project.controller;

import cnsr.fjh03.project.common.R;
import cnsr.fjh03.project.pojo.AddressBook;
import cnsr.fjh03.project.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: AddressBookController
 * @Date: 2025/2/14
 * @Time: 9:08
 * @Description:添加自定义描述
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook) {
        log.info(addressBook.toString());
        addressBookService.mysave(addressBook);
        return R.success(addressBook);
    }

    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
        log.info(addressBook.toString());
        return R.success(addressBookService.setDefault(addressBook));
    }

    @GetMapping("default")
    public R getDefault() {
        AddressBook addressBook = addressBookService.getDefault();
        if (addressBook == null) {
            return R.error("没有找到该对象");
        } else {
            return R.success(addressBook);
        }
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        if (addressBook != null) {
            return R.success(addressBook);
        } else {
            return R.error("没有找到该对象");
        }
    }


    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook) {
        log.info("addressBook:{}", addressBook);
        return R.success(addressBookService.list(addressBook));
    }
}
