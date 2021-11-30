package com.gitee.coadmin.modules.equip.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javax.validation.constraints.*;
import com.gitee.coadmin.base.BaseDto;

/**
 * 设备
 * @author randolph
 * @since 2021-11-30
 */
@Getter
@Setter
@NoArgsConstructor
public class DeviceDTO extends BaseDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using= ToStringSerializer.class) // 防止精度丢失
    private Long id;

    @ApiModelProperty(value = "名称")
    private String deviceName;

    @ApiModelProperty(value = "密钥")
    private Integer deviceId;

    @ApiModelProperty(value = "状态")
    private Boolean status;

    @ApiModelProperty(value = "创建日期")
    private Timestamp createTime;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "上次上线时间")
    private Timestamp latestOnlineTime;

    private Timestamp updateTime;

    @ApiModelProperty(value = "拥有者")
    private String openid;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      DeviceDTO obj = (DeviceDTO) o;
      return Objects.equals(id, obj.id);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }
}
