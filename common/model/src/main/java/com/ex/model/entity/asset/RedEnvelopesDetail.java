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
 * (red_envelopes_detail)实体类
 *
 * @author 
 * @since 2020-11-19 16:11:11
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("red_envelopes_detail")
public class RedEnvelopesDetail extends Model<RedEnvelopesDetail> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
	private Integer id;
    /**
     * 红包id
     */
    private Long redId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 抢到的红包金额
     */
    private BigDecimal amount;
    /**
     * createTime
     */
    private Date createTime;

}