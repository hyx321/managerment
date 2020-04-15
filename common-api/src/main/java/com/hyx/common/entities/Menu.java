package com.hyx.common.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/14 17:17
 * 菜单列表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private int id;
    private String name;
    private List<MenuBase> menuBaseList;
}
