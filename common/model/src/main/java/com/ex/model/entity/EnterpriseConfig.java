package com.ex.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import lombok.Data;

/**
 * (enterprise_config)实体类
 *
 * @author kancy
 * @since 2020-11-02 14:58:34
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@TableName("enterprise_config")
public class EnterpriseConfig {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * 用户id
     */
    private Integer enterpriseId;
    /**
     * 用户名
     */
    private String enterpriseName;
    /**
     * 绑定ip
     */
    private String bindIp;
    /**
     * 公钥
     */
    private String accessKey;
    /**
     * 私钥
     */
    private String secretKey;
    /**
     * aes key
     */
    private String aesKey;
    /**
     * aes iv
     */
    private String aseIv;
    /**
     * 交易权限 0未授权 1授权
     */
    private Integer trade;
    /**
     * 提币权限 0未授权 1授权
     */
    private Integer withdraw;
    /**
     * 提币密码
     */
    private String withdrawPwd;
    /**
     * 是否锁定 0否1是
     */

    private Integer isLock;
    /**
     * 是否生效 0否1是
     */
    private Integer valid;
    /**
     * 是否删除 0否1是
     */
    private Integer isDelete;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否白名单0否1是
     */
    private Integer whiteUser;

}