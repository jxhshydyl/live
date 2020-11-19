package com.ex.model.entity.asset;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 提现订单(withdrawal_order)实体类
 *
 * @author 
 * @since 2020-11-19 16:11:11
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("withdrawal_order")
public class WithdrawalOrder extends Model<WithdrawalOrder> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
	private Integer id;
    /**
     * userId
     */
    private Long userId;
    /**
     * 用户绑定的银行卡id
     */
    private Long bankId;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 开户行
     */
    private String openBank;
    /**
     * 资产id
     */
    private Integer assetId;
    /**
     * 提现金额
     */
    private BigDecimal amount;
    /**
     * 手续费
     */
    private BigDecimal fee;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * 到账时间
     */
    private Date paymentDate;
    /**
     * 0：已提交  1：成功 2：失败
     */
    private Integer status;
    /**
     * updateTime
     */
    @TableField(update = "now()")
	private Date updateTime;

}