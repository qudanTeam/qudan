
package com.zhangheng.springboot.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "服务返回状态VO")
@Getter
@Setter
public class ResultVo<T> {

	@ApiModelProperty(value = "返回码")
    private String statusCode;

	@ApiModelProperty(value = "返回描述")
    private String statusMessage;

    @ApiModelProperty(value = "业务数据")
    private T data;

}
