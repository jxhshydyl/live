package com.ex.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * (user_info)实体类
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_info")
public class UserInfo {
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
     * 个人简介
     */
    private String introduce;
    /**
     * 会员类型 会员类型  1：月卡   2：季卡  3：半年  4：一年
     */
    private Integer memberType;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像路径
     */
    private String headPortrait;
    /**
     * 性别  1：男  0：女
     */
    private Integer sex;
    /**
     * 所在地
     */
    private String location;
    /**
     * 标签
     */
    private String label;
    /**
     * 背景墙图片
     */
    private String backWallImg;
    /**
     * 点赞
     */
    private Long thumbsCount;
    /**
     * 关注
     */
    private Integer concernCount;
    /**
     * 粉丝数
     */
    private Integer fansCount;
    /**
     * VIP 等级
     */
    private Integer vipLevel;
    /**
     * 经验等级
     */
    private Integer experienceLevel;
    /**
     * 经验值
     */
    private Integer experienceValue;
    /**
     * 上一次签到时间
     */
    private Date lastSignTime;
    /**
     * 连续签到次数
     */
    private Integer contDays;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * updateTime
     */
    private Date updateTime;

}