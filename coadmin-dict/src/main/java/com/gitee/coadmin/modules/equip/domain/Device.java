package com.gitee.coadmin.modules.equip.domain;

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

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import com.gitee.coadmin.base.BaseEntity;

/**
 * 设备
 * @author randolph
 * @since 2021-11-30
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("t_device")
public class Device extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(type= IdType.AUTO)
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

    @ApiModelProperty(value = "拥有者")
    private String openid;

    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device obj = (Device) o;
        return Objects.equals(id, obj.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void copyFrom(Device source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
