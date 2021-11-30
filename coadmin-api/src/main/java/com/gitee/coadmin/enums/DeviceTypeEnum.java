package com.gitee.coadmin.enums;

import lombok.Getter;

@Getter
public enum DeviceTypeEnum {
    // 设备类型 1-电脑 2-灯光 3-电源 4-投影仪
    COMPUTER(1,"电脑"),
    LAMP(2,"灯光"),
    ;


    private Integer value;
    private String description;

    public static DeviceTypeEnum findDesc(Integer value) {
        for(DeviceTypeEnum feedbackEnum: DeviceTypeEnum.values()){
            if(value.equals(feedbackEnum.getValue())){
                return feedbackEnum;
            }
        }
        return null;
    }

    DeviceTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
