package com.gitee.coadmin.modules.equip.service.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gitee.coadmin.base.PageInfo;
import com.gitee.coadmin.modules.equip.domain.Device;
import com.gitee.coadmin.modules.equip.service.dto.DeviceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author randolph
 * @since 2021-11-30
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeviceConverter {
    /**
     * DTO转Entity
     * @param dto /
     * @return /
     */
    Device toEntity(DeviceDTO dto);

    /**
     * Entity转DTO
     * @param entity /
     * @return /
     */
    DeviceDTO toDto(Device entity);

    /**
     * DTO集合转Entity集合
     * @param dtoList /
     * @return /
     */
    List<Device> toEntity(List<DeviceDTO> dtoList);

    /**
     * Entity集合转DTO集合
     * @param entityList /
     * @return /
     */
    List <DeviceDTO> toDto(List<Device> entityList);

    default PageInfo<DeviceDTO> convertPage(IPage<Device> page) {
        if (page == null) {
            return null;
        }
        PageInfo<DeviceDTO> pageInfo = new PageInfo<>();
        pageInfo.setTotalElements(page.getTotal());
        pageInfo.setContent(toDto(page.getRecords()));
        return pageInfo;
    }
}
