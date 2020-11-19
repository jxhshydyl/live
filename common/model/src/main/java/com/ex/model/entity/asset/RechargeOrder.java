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
 * 充值订单(recharge_order)实体类
 *
 * @author 
 * @since 2020-11-19 16:11:11
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("recharge_order")
public class RechargeOrder extends Model<RechargeOrder> implements Serializable {
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
     * 充值金额
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
     * 0：未到账   1：成功 2：失败
     */
    private Integer status;
    /**
     * updateTime
     */
    @TableField(update = "now()")
	private Date updateTime;

}