package com.gitee.coadmin.modules.system.service.dto;

import com.gitee.coadmin.base.DataDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
* @author jinjin
* @date 2020-09-24
*/
@Getter
@Setter
@NoArgsConstructor
public class DictDto extends DataDto implements Serializable {
    private Long id;
    private String name;
    private String description;
}
