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
 * 兑换订单(exchange_order)实体类
 *
 * @author 
 * @since 2020-11-19 16:11:11
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("exchange_order")
public class ExchangeOrder extends Model<ExchangeOrder> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
	private Integer id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 0：充值兑换  1：余额兑换
     */
    private Integer type;
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
     * 账户
     */
    private String account;
    /**
     * 买方id
     */
    private Integer buyAssetId;
    /**
     * 买方名称
     */
    private String buyAssetName;
    /**
     * 买方金额
     */
    private BigDecimal buyAmount;
    /**
     * 卖方id
     */
    private Integer sellAssetId;
    /**
     * 卖方名称
     */
    private String sellAssetName;
    /**
     * 卖方金额
     */
    private BigDecimal sellAmount;
    /**
     * 赠送
     */
    private BigDecimal give;
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