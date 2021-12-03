package com.gitee.coadmin.modules.wechat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gitee.coadmin.base.impl.CommonServiceImpl;
import lombok.RequiredArgsConstructor;
import com.gitee.coadmin.utils.QueryHelpMybatisPlus;
import com.gitee.coadmin.base.PageInfo;
import com.gitee.coadmin.utils.PageUtil;
import com.gitee.coadmin.modules.wechat.domain.WechatUser;
import com.gitee.coadmin.modules.wechat.service.WechatUserService;
import com.gitee.coadmin.modules.wechat.service.dto.WechatUserDTO;
import com.gitee.coadmin.modules.wechat.service.dto.WechatUserQueryParam;
import com.gitee.coadmin.modules.wechat.service.mapper.WechatUserMapper;
import com.gitee.coadmin.modules.wechat.service.converter.WechatUserConverter;
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
 * @since 2021-12-01
 */
@Service
@RequiredArgsConstructor
// @CacheConfig(cacheNames = WechatUserService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WechatUserServiceImpl extends CommonServiceImpl<WechatUserMapper, WechatUser> implements WechatUserService {

    // private final RedisUtils redisUtils;
    private final WechatUserMapper wechatUserMapper;
    private final WechatUserConverter wechatUserConverter;

    @Override
    public PageInfo<WechatUserDTO> queryAll(WechatUserQueryParam query, Pageable pageable) {
        IPage<WechatUser> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<WechatUser> page = wechatUserMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return wechatUserConverter.convertPage(page);
    }

    @Override
    public List<WechatUserDTO> queryAll(WechatUserQueryParam query){
        return wechatUserConverter.toDto(wechatUserMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)));
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public WechatUserDTO getById(Integer id) {
        if (id == null) {
            return null;
        }
        return wechatUserConverter.toDto(wechatUserMapper.selectById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(WechatUserDTO res) {
        WechatUser entity = wechatUserConverter.toEntity(res);
        int ret = wechatUserMapper.insert(entity);
        res.setId(entity.getId());
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(WechatUserDTO res){
        WechatUser entity = wechatUserConverter.toEntity(res);
        int ret = wechatUserMapper.updateById(entity);
        // delCaches(res.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return wechatUserMapper.deleteBatchIds(ids);
    }

    @Override
    public WechatUserDTO findByOpenid(String openid) {
        WechatUser wechatUser = lambdaQuery().eq(WechatUser::getOpenid,openid).one();
        return wechatUserConverter.toDto(wechatUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeById(Integer id){
        Set<Integer> set = new HashSet<>(1);
        set.add(id);
        return this.removeByIds(set);
    }

    /*
    private void delCaches(Integer id) {
        redisUtils.delByKey(CACHE_KEY + "::id:", id);
    }

    private void delCaches(Set<Integer> ids) {
        for (Integer id: ids) {
            delCaches(id);
        }
    }*/
}
