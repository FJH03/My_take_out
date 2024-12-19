package cnsr.fjh03.project.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: OperateLog
 * @Date: 2023/12/18
 * @Time: 10:43
 * @Description:添加自定义描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Long operateUser;
    private LocalDateTime operateTime;
    private String className;
    private String methodName;
    private String methodParams;
    private String returnValue;
    private Long costTime;
}
