package com.gitee.coadmin.enums;

import lombok.Getter;

@Getter
public enum DeviceStatusEnum {
    OFF_LINE(0,false),
    ON_LINE(1,true),
    ;
    private Integer value;
    private Boolean flag;

    public static Boolean findBoolean(Integer value){
        for(DeviceStatusEnum deviceStatusEnum : DeviceStatusEnum.values()){
            if(deviceStatusEnum.getValue().equals(value)){
                return deviceStatusEnum.getFlag();
            }
        }
        return null;
    }

    DeviceStatusEnum(Integer value, Boolean flag) {
        this.value = value;
        this.flag = flag;
    }
}
