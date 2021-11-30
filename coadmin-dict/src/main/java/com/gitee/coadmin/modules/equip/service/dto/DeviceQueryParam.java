package com.gitee.coadmin.modules.equip.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Date;
import com.gitee.coadmin.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author randolph
 * @since 2021-11-30
 */
@Getter
@Setter
public class DeviceQueryParam{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String deviceName;

}
