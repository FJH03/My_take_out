package cnsr.fjh03.project.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: MyfileMethods
 * @Date: 2023/12/12
 * @Time: 11:05
 * @Description:添加自定义描述
 */
@Data
@Slf4j
public class MyfileMethods {
    private  Map<Long, String> backupFiles = new HashMap<>();
    public void backep(Long id, String filename) {
        File originalFile = new File(filename);
        if (originalFile.exists()) {
            String backupFileName = filename + ".bak";
            File backupFile = new File(backupFileName);
            if (originalFile.renameTo(backupFile)) {
                backupFiles.put(id, backupFileName);
                log.info("成功备份文件！");
            } else {
                log.info("备份文件失败！");
            }
        } else {
            log.info("文件不存在！");
        }
    }

    public void deletebackep() {
        for (String backupFileName : backupFiles.values()) {
            File backupFile = new File(backupFileName);
            if (backupFile.exists() && backupFile.delete()) {
                log.info("删除备份文件成功: {}", backupFileName);
            } else {
                log.error("删除备份文件失败: {}", backupFileName);
            }
        }
    }

    public void backepTofile() {
        for (Map.Entry<Long, String> entry : backupFiles.entrySet()) {
            String backupFileName = entry.getValue();
            File backupFile = new File(backupFileName);
            String originalFileName = backupFileName.substring(0, backupFileName.length() - 4);  // 去除后缀".bak"
            File originalFile = new File(originalFileName);
            if (backupFile.exists() && backupFile.renameTo(originalFile)) {
                log.info("文件回滚成功: {}", originalFileName);
            } else {
                log.error("文件回滚失败: {}", originalFileName);
            }
        }
    }
}
