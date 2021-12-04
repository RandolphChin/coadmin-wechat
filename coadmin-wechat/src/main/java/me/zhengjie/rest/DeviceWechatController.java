package me.zhengjie.rest;

import com.gitee.coadmin.annotation.UnifiedAPI;
import com.gitee.coadmin.base.PageInfo;
import com.gitee.coadmin.modules.equip.service.DeviceService;
import com.gitee.coadmin.modules.equip.service.dto.DeviceDTO;
import com.gitee.coadmin.modules.equip.service.dto.DeviceQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@UnifiedAPI
@RestController
@RequiredArgsConstructor
@Api(tags = "设备")
@RequestMapping("/wechat/api/device")
public class DeviceWechatController {
    private final DeviceService deviceService;
    @GetMapping
    @ApiOperation("查询设备")
    public PageInfo<DeviceDTO> query(DeviceQueryParam query, Pageable pageable){
        return deviceService.queryAll(query,pageable);
    }


    @PutMapping
    @ApiOperation("修改设备")
    public Integer update(@Validated @RequestBody DeviceDTO res){
        return deviceService.updateById(res);
    }

    @DeleteMapping
    @ApiOperation("删除设备")
    public Integer delete(@RequestBody Set<Long> ids) {
        return deviceService.removeByIds(ids);
    }
}
