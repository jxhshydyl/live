package com.ex.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname UserSignRecord
 * @Description TODO
 * @Date 2020/11/15 17:11
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_sign_record")
public class UserSignRecord {
    private Long id;
    private Long userId;
    private Integer exp;
    private BigDecimal integralNum;
    private String dateTime;
    private Date createTime;
}
