package com.gitee.coadmin.modules.equip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gitee.coadmin.base.impl.CommonServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.gitee.coadmin.utils.QueryHelpMybatisPlus;
import com.gitee.coadmin.base.PageInfo;
import com.gitee.coadmin.utils.PageUtil;
import com.gitee.coadmin.modules.equip.domain.Device;
import com.gitee.coadmin.modules.equip.service.DeviceService;
import com.gitee.coadmin.modules.equip.service.dto.DeviceDTO;
import com.gitee.coadmin.modules.equip.service.dto.DeviceQueryParam;
import com.gitee.coadmin.modules.equip.service.mapper.DeviceMapper;
import com.gitee.coadmin.modules.equip.service.converter.DeviceConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import java.util.*;

/**
 * @author randolph
 * @since 2021-11-30
 */
@Service
@RequiredArgsConstructor
// @CacheConfig(cacheNames = DeviceService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeviceServiceImpl extends CommonServiceImpl<DeviceMapper, Device> implements DeviceService {

    // private final RedisUtils redisUtils;
    private final DeviceMapper deviceMapper;
    private final DeviceConverter deviceConverter;

    @Override
    public PageInfo<DeviceDTO> queryAll(DeviceQueryParam query, Pageable pageable) {
        IPage<Device> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<Device> page = deviceMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return deviceConverter.convertPage(page);
    }

    @Override
    public List<DeviceDTO> queryAll(DeviceQueryParam query){
        return deviceConverter.toDto(deviceMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)));
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public DeviceDTO getById(Long id) {
        if (id == null) {
            return null;
        }
        return deviceConverter.toDto(deviceMapper.selectById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(DeviceDTO res) {
        Device entity = deviceConverter.toEntity(res);
        int ret = deviceMapper.insert(entity);
        res.setId(entity.getId());
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(DeviceDTO res){
        Device entity = deviceConverter.toEntity(res);
        int ret = deviceMapper.updateById(entity);
        // delCaches(res.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Long> ids){
        // delCaches(ids);
        return deviceMapper.deleteBatchIds(ids);
    }

    @Override
    public Device getByDeviceId(Integer deviceId) {
        Device device = lambdaQuery().eq(Device::getDeviceId,deviceId).one();
        return device;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeById(Long id){
        Set<Long> set = new HashSet<>(1);
        set.add(id);
        return this.removeByIds(set);
    }

    /*
    private void delCaches(Long id) {
        redisUtils.delByKey(CACHE_KEY + "::id:", id);
    }

    private void delCaches(Set<Long> ids) {
        for (Long id: ids) {
            delCaches(id);
        }
    }*/
}
