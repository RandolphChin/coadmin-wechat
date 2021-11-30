package com.gitee.coadmin.modules.equip.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class DeviceStatusReceiveDTO {
    @JsonProperty(value = "device_id")
    private Integer deviceId;
    @JsonProperty("device_status")
    private Integer deviceStatus;
}
