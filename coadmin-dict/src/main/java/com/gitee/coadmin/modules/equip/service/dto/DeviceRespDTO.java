package com.gitee.coadmin.modules.equip.service.dto;

import lombok.Data;

@Data
public class DeviceRespDTO {
    private String status;
    private String msg;

    public static DeviceRespDTO build(String status,String msg){
        DeviceRespDTO dto = new DeviceRespDTO();
        dto.setStatus(status);
        dto.setMsg(msg);
        return dto;
    }
}
