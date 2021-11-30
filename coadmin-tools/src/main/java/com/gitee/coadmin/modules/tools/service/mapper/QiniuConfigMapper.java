package com.gitee.coadmin.modules.tools.service.mapper;

import com.gitee.coadmin.base.CommonMapper;
import com.gitee.coadmin.modules.tools.domain.QiniuConfig;
import org.springframework.stereotype.Repository;

/**
* @author jinjin
* @date 2020-09-27
*/
@Repository
public interface QiniuConfigMapper extends CommonMapper<QiniuConfig> {

    int updateType(String type);
}
