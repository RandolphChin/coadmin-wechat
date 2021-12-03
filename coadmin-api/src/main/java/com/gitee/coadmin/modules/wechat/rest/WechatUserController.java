package com.gitee.coadmin.modules.wechat.rest;

import com.gitee.coadmin.annotation.UnifiedAPI;
import com.gitee.coadmin.base.PageInfo;
import com.gitee.coadmin.modules.logging.annotation.Log;
import com.gitee.coadmin.modules.logging.annotation.type.LogActionType;
import com.gitee.coadmin.modules.wechat.service.WechatUserService;
import com.gitee.coadmin.modules.wechat.service.dto.WechatUserDTO;
import com.gitee.coadmin.modules.wechat.service.dto.WechatUserQueryParam;
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
    VALUES (null, 4, 1, '微信用户', 'wxyh', 'WechatUser', 'wechat-user/index', 10, '', 0, 0, 0, 'wechatUser:list');
SELECT @lastId:=LAST_INSERT_ID();
INSERT INTO sys_menu(pid, sub_count, `type`, title, sort, i_frame, `cache`, hidden, permission)
    VALUES (@lastId, 0, 2, '查看微信用户', 10, 0, 0, 0, 'wechatUser:list');
INSERT INTO sys_menu(pid, sub_count, `type`, title, sort, i_frame, `cache`, hidden, permission)
    VALUES (@lastId, 0, 2, '新增微信用户', 20, 0, 0, 0, 'wechatUser:add');
INSERT INTO sys_menu(pid, sub_count, `type`, title, sort, i_frame, `cache`, hidden, permission)
    VALUES (@lastId, 0, 2, '修改微信用户', 30, 0, 0, 0, 'wechatUser:edit');
INSERT INTO sys_menu(pid, sub_count, `type`, title, sort, i_frame, `cache`, hidden, permission)
    VALUES (@lastId, 0, 2, '删除微信用户', 40, 0, 0, 0, 'wechatUser:del');
*/

/**
 * @author randolph
 * @since 2021-12-01
 **/
@UnifiedAPI
@RestController
@RequiredArgsConstructor
@Api(tags = "微信用户")
@RequestMapping("/api/wechat-user")
public class WechatUserController {

    private final WechatUserService wechatUserService;

    @GetMapping
    @Log("查询微信用户")
    @ApiOperation("查询微信用户")
    @PreAuthorize("@el.check('wechatUser:list')")
    public PageInfo<WechatUserDTO> query(WechatUserQueryParam query, Pageable pageable){
        return wechatUserService.queryAll(query,pageable);
    }

    @PostMapping
    @Log(value = "新增微信用户", type = LogActionType.ADD)
    @ApiOperation("新增微信用户")
    @PreAuthorize("@el.check('wechatUser:add')")
    public Integer create(@Validated @RequestBody WechatUserDTO res){
        return wechatUserService.insert(res);
    }

    @PutMapping
    @Log(value = "修改微信用户", type = LogActionType.UPDATE)
    @ApiOperation("修改微信用户")
    @PreAuthorize("@el.check('wechatUser:edit')")
    public Integer update(@Validated @RequestBody WechatUserDTO res){
        return wechatUserService.updateById(res);
    }

    @DeleteMapping
    @Log(value = "删除微信用户", type = LogActionType.DELETE)
    @ApiOperation("删除微信用户")
    @PreAuthorize("@el.check('wechatUser:del')")
    public Integer delete(@RequestBody Set<Integer> ids) {
        return wechatUserService.removeByIds(ids);
    }

    @Log("导出微信用户")
    @ApiOperation("导出微信用户")
    @UnifiedAPI(enable = false)
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('wechatUser:list')")
    public void download(HttpServletResponse response, WechatUserQueryParam criteria) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode("导出微信用户", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), WechatUserDTO.class)
            .sheet("微信用户")
            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
            .doWrite(wechatUserService.queryAll(criteria));
    }
}
