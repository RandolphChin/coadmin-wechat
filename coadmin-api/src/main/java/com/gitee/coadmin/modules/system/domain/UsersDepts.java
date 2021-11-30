package com.gitee.coadmin.modules.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户机构关联
 * Created by jinjin on 2020-10-08.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = { "handler" })
@TableName("sys_users_depts")
@ApiModel(value="UsersDepts对象", description="用户机构关联")
public class UsersDepts implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableField(value = "user_id")
    private Long userId;

    @ApiModelProperty(value = "机构ID")
    @TableField(value = "dept_id")
    private Long deptId;
}
