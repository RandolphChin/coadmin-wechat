package com.gitee.coadmin.modules.equip.rest;

import com.gitee.coadmin.annotation.UnifiedAPI;
import com.gitee.coadmin.base.PageInfo;
import com.gitee.coadmin.modules.equip.service.DeviceService;
import com.gitee.coadmin.modules.equip.service.dto.DeviceDTO;
import com.gitee.coadmin.modules.equip.service.dto.DeviceQueryParam;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.util.Set;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.excel.EasyExcel;
import com.gitee.coadmin.modules.system.service.dto.UserQueryParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


/*  添加菜单的 SQL
INSERT INTO sys_menu(pid, sub_count, `type`, title, title_letter, component_name, `component`, sort, `path`, i_frame, `cache`, hidden, permission)
    VALUES (null, 4, 1, '设备', 'sb', 'Device', 'device/index', 10, '', 0, 0, 0, 'device:list');
SELECT @lastId:=LAST_INSERT_ID();
INSERT INTO sys_menu(pid, sub_count, `type`, title, sort, i_frame, `cache`, hidden, permission)
    VALUES (@lastId, 0, 2, '查看设备', 10, 0, 0, 0, 'device:list');
INSERT INTO sys_menu(pid, sub_count, `type`, title, sort, i_frame, `cache`, hidden, permission)
    VALUES (@lastId, 0, 2, '新增设备', 20, 0, 0, 0, 'device:add');
INSERT INTO sys_menu(pid, sub_count, `type`, title, sort, i_frame, `cache`, hidden, permission)
    VALUES (@lastId, 0, 2, '修改设备', 30, 0, 0, 0, 'device:edit');
INSERT INTO sys_menu(pid, sub_count, `type`, title, sort, i_frame, `cache`, hidden, permission)
    VALUES (@lastId, 0, 2, '删除设备', 40, 0, 0, 0, 'device:del');
*/

/**
 * @author randolph
 * @since 2021-11-30
 **/
@UnifiedAPI
@RestController
@RequiredArgsConstructor
@Api(tags = "设备")
@RequestMapping("/api/device")
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    @ApiOperation("查询设备")
    @PreAuthorize("@el.check('device:list')")
    public PageInfo<DeviceDTO> query(DeviceQueryParam query, Pageable pageable){
        return deviceService.queryAll(query,pageable);
    }

    @PostMapping
    @ApiOperation("新增设备")
    @PreAuthorize("@el.check('device:add')")
    public Integer create(@Validated @RequestBody DeviceDTO res){
        return deviceService.insert(res);
    }

    @PutMapping
    @ApiOperation("修改设备")
    @PreAuthorize("@el.check('device:edit')")
    public Integer update(@Validated @RequestBody DeviceDTO res){
        return deviceService.updateById(res);
    }

    @DeleteMapping
    @ApiOperation("删除设备")
    @PreAuthorize("@el.check('device:del')")
    public Integer delete(@RequestBody Set<Long> ids) {
        return deviceService.removeByIds(ids);
    }

    @ApiOperation("导出设备")
    @UnifiedAPI(enable = false)
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('device:list')")
    public void download(HttpServletResponse response, DeviceQueryParam criteria) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode("导出设备", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), DeviceDTO.class)
            .sheet("设备")
            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
            .doWrite(deviceService.queryAll(criteria));
    }
}
