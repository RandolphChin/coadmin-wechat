package com.gitee.coadmin.modules.wechat.service;

import com.gitee.coadmin.base.CommonService;
import com.gitee.coadmin.base.PageInfo;
import com.gitee.coadmin.modules.wechat.domain.WechatUser;
import com.gitee.coadmin.modules.wechat.service.dto.WechatUserDTO;
import com.gitee.coadmin.modules.wechat.service.dto.WechatUserQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
 * 微信用户
 * @author randolph
 * @since 2021-12-01
 */
public interface WechatUserService extends CommonService<WechatUser> {

    String CACHE_KEY = "wechat-user/wechat-user";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<WechatUserDTO>
    */
    PageInfo<WechatUserDTO> queryAll(WechatUserQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<WechatUserDTO>
    */
    List<WechatUserDTO> queryAll(WechatUserQueryParam query);

    WechatUserDTO getById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(WechatUserDTO res);
    int updateById(WechatUserDTO res);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    WechatUserDTO findByOpenid(String openid);
}
