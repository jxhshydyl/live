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
 * (red_envelopes_order)实体类
 *
 * @author 
 * @since 2020-11-19 16:11:11
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("red_envelopes_order")
public class RedEnvelopesOrder extends Model<RedEnvelopesOrder> implements Serializable {
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
     * 红包类型   1：随机红包   2：普通红包
     */
    private Integer type;
    /**
     * 聊天类型   1：群聊   2：单聊
     */
    private Integer chatType;
    /**
     * 群聊为房间号    单聊为目标用户id
     */
    private Integer targetId;
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
     * 红包个数
     */
    private Integer number;
    /**
     * 剩余数量
     */
    private Integer surplusNumber;
    /**
     * 标语
     */
    private String slogan;
    /**
     * 手续费
     */
    private BigDecimal fee;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * 过期时间
     */
    private Date expireTime;
    /**
     * updateTime
     */
    @TableField(update = "now()")
	private Date updateTime;

}