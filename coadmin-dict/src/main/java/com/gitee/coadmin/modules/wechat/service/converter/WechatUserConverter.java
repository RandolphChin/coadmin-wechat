package com.gitee.coadmin.modules.wechat.service.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gitee.coadmin.base.PageInfo;
import com.gitee.coadmin.modules.wechat.domain.WechatUser;
import com.gitee.coadmin.modules.wechat.service.dto.WechatUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author randolph
 * @since 2021-12-01
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WechatUserConverter {
    /**
     * DTO转Entity
     * @param dto /
     * @return /
     */
    WechatUser toEntity(WechatUserDTO dto);

    /**
     * Entity转DTO
     * @param entity /
     * @return /
     */
    WechatUserDTO toDto(WechatUser entity);

    /**
     * DTO集合转Entity集合
     * @param dtoList /
     * @return /
     */
    List<WechatUser> toEntity(List<WechatUserDTO> dtoList);

    /**
     * Entity集合转DTO集合
     * @param entityList /
     * @return /
     */
    List <WechatUserDTO> toDto(List<WechatUser> entityList);

    default PageInfo<WechatUserDTO> convertPage(IPage<WechatUser> page) {
        if (page == null) {
            return null;
        }
        PageInfo<WechatUserDTO> pageInfo = new PageInfo<>();
        pageInfo.setTotalElements(page.getTotal());
        pageInfo.setContent(toDto(page.getRecords()));
        return pageInfo;
    }
}
