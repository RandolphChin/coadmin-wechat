package com.gitee.coadmin.modules.wechat.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import java.util.Date;
import java.util.Objects;
import com.gitee.coadmin.base.BaseEntity;

/**
 * 微信用户
 * @author randolph
 * @since 2021-12-01
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("wechat_user")
public class WechatUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "openid")
    private String openid;

    private String password;

    @ApiModelProperty(value = "gender")
    private Integer gender;

    @Create
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
        WechatUser obj = (WechatUser) o;
        return Objects.equals(id, obj.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void copyFrom(WechatUser source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
