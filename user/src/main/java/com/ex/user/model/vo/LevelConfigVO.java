package com.ex.user.model.vo;

import com.ex.model.entity.base.AuthConfig;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Classname LevelConfigVO
 * @Description TODO
 * @Date 2020/11/15 20:22
 * @Author by liuweipeng
 */
@Data
public class LevelConfigVO {
    /**
     * id
     */
    private Integer id;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 经验值
     */
    private Integer exp;
    /**
     * createTime
     */
    private Date createTime;

    private List<AuthConfig> authConfigs;

}
