package com.ex.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * (apikey)实体类
 *
 * @author kancy
 * @since 2020-11-02 14:47:08
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
public class Apikey {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * userid
     */
    private Long userid;
    /**
     * username
     */
    private String username;
    /**
     * name
     */
    private String name;
    /**
     * bindip
     */
    private String bindip;
    /**
     * accesskey
     */
    private String accesskey;
    /**
     * secretkey
     */
    private String secretkey;
    /**
     * tradeshell
     */
    private Integer tradeshell;
    /**
     * payoutshell
     */
    private Integer payoutshell;
    /**
     * payoutpwd
     */
    private String payoutpwd;
    /**
     * islock
     */
    private Integer islock;
    /**
     * isact
     */
    private Integer isact;
    /**
     * isdel
     */
    private Integer isdel;
    /**
     * addtime
     */
    private Date addtime;

}