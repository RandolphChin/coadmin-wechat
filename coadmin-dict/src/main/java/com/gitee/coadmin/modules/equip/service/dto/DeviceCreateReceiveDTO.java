package com.gitee.coadmin.modules.equip.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *   {
 * "device_id": 11, // 设备唯一性标识
 * "device_type": 1, // 设备类型 1-电脑 2-灯光 3-电源 4-投影仪
 * "device_owner":"字符串", // 微信小程序通过UDP 传送给设备，原样上传给服务器，内容为加密的微信用户信息，共172个字节
 * "timestamp": 1451552400 , // 时间戳，每次上报时唯一
 * }
 */
@Data
public class DeviceCreateReceiveDTO {
    @JsonProperty(value = "device_id")
    private Integer deviceId;
    @JsonProperty("device_type")
    private Integer deviceType;
    @JsonProperty("device_owner")
    private String deviceOwner;
}
