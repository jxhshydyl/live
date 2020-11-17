package com.ex.user.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Classname Message
 * @Description
 * @Date 2020/11/11 18:50
 */
@Data
@ApiModel(value = "短信DTO")
public class MessageDTO {
    /**
     * 发送短信的类型 EnumMessageBusinessType
     */
    @ApiModelProperty(name = "businessType", value = "短信类型", required = true)
    @NotNull(message = "短信类型不能为空")
    private Integer businessType;
    @ApiModelProperty(name = "countryCode", value = "国家码")
    private String countryCode;
    @ApiModelProperty(name = "receiveAddress", value = "接受地址/已登录账户不需要")
    private String receiveAddress;
    @JsonIgnore
    private String ip;
}
