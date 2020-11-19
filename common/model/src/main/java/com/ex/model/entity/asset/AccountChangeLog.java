package com.ex.model.entity.asset;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * OTC流水表(account_change_log)实体类
 *
 * @author 
 * @since 2020-11-19 16:11:11
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("account_change_log")
public class AccountChangeLog extends Model<AccountChangeLog> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
	private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 账户ID
     */
    private Long accountId;
    /**
     * 对手用户ID 0-内部户
     */
    private Integer opponentAccountId;
    /**
     * 资产ID
     */
    private Integer assetId;
    /**
     * 流水类型 1-充值 2-提现 3-兑换 4-刷礼物 5-打赏 6-发出红包  7-抢到红包 8-直播收入 9-打赏收入 10-vip开通 11-任务获得
     */
    private Integer type;
    /**
     * 订单ID
     */
    private Integer orderId;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 修改后总余额
     */
    private BigDecimal newBalance;
    /**
     * 创建时间
     */
    private Date createTime;

}