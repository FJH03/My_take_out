package cnsr.fjh03.project.controller;

import cnsr.fjh03.project.common.R;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: CommonController
 * @Date: 2025/1/12
 * @Time: 14:01
 * @Description:添加自定义描述
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {

    @Value("${my_takeout.path}")
    private String Path;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        log.info(file.toString());
        String filename = file.getOriginalFilename();
        //获取上传文件的后缀suffix
        String suffix = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID() + suffix;
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(Path + filename));
            return R.success(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error("上传失败！");
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse resp) throws IOException {
        log.info(name);
        ServletOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            //输入流，读取文件内容
            fileInputStream = new FileInputStream(Path + name);
            //输出流，通过输出流将文件返回浏览器
            outputStream = resp.getOutputStream();

            String suffix = name.substring(name.lastIndexOf("."));
            resp.setContentType("/image/" + suffix);

            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }

            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }
}
