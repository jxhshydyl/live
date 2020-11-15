package com.ex.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户开通会员详情(user_member_detail)实体类
 *
 * @author 
 * @since 2020-11-15 19:48:00
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_member_detail")
public class UserMemberDetail {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * userId
     */
    private Long userId;
    /**
     * 会员类型  1：月卡   2：季卡   3：半年   4：一年
     */
    private Integer memberType;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * createTime
     */
    private Date createTime;

}