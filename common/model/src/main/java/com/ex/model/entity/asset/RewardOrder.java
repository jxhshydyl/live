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
 * 打赏订单(reward_order)实体类
 *
 * @author 
 * @since 2020-11-19 16:11:11
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("reward_order")
public class RewardOrder extends Model<RewardOrder> implements Serializable {
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
     * 目标用户id
     */
    private Long targetUserId;
    /**
     * 资产id
     */
    private Integer assetId;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 金额
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