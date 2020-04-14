package com.hyx.common.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/8 14:29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private  T      data;

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
