package com.gitee.coadmin.modules.wechat.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javax.validation.constraints.*;
import com.gitee.coadmin.base.BaseDto;

/**
 * 微信用户
 * @author randolph
 * @since 2021-12-01
 */
@Getter
@Setter
@NoArgsConstructor
public class WechatUserDTO extends BaseDto {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "openid")
    @NotBlank
    private String openid;

    private String password;

    @ApiModelProperty(value = "gender")
    private Integer gender;

    @ApiModelProperty(value = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "nickname")
    private String nickname;

    @ApiModelProperty(value = "avatar")
    private String avatar;

    private Date lastLoginTime;

    private String lastLoginIp;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      WechatUserDTO obj = (WechatUserDTO) o;
      return Objects.equals(id, obj.id);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }
}
