package com.gitee.coadmin.modules.equip.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class DeviceOrderDTO {
    @JsonProperty(value = "device_id")
    private Integer deviceId;
    @JsonProperty("device_order")
    private Integer deviceOrder;
    private Long timestamp;
    @JsonProperty("cron_expression")
    private String cronExpression;
}
