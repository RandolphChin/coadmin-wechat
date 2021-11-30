package com.gitee.coadmin.modules.equip.service;

import com.gitee.coadmin.base.CommonService;
import com.gitee.coadmin.base.PageInfo;
import com.gitee.coadmin.modules.equip.domain.Device;
import com.gitee.coadmin.modules.equip.service.dto.DeviceDTO;
import com.gitee.coadmin.modules.equip.service.dto.DeviceQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
 * 设备
 * @author randolph
 * @since 2021-11-30
 */
public interface DeviceService extends CommonService<Device> {

    String CACHE_KEY = "device/device";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<DeviceDTO>
    */
    PageInfo<DeviceDTO> queryAll(DeviceQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<DeviceDTO>
    */
    List<DeviceDTO> queryAll(DeviceQueryParam query);

    DeviceDTO getById(Long id);

    /**
     * 插入一条新数据。
     */
    int insert(DeviceDTO res);
    int updateById(DeviceDTO res);
    int removeById(Long id);
    int removeByIds(Set<Long> ids);

    Device getByDeviceId(Integer deviceId);
}
