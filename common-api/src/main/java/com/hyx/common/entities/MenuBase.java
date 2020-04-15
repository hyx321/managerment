package com.hyx.common.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/14 17:54
 * 二级菜单列表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuBase implements Serializable {
    private int id;
    private String name;
}
