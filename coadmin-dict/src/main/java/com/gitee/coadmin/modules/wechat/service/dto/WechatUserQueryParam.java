package com.gitee.coadmin.modules.wechat.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Date;
import com.gitee.coadmin.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author randolph
 * @since 2021-12-01
 */
@Getter
@Setter
public class WechatUserQueryParam{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String openid;

}
