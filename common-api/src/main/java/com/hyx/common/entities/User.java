package com.hyx.common.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    private String name;
    private String password;
    private String salt;
    private String email;
    private String is_active;
    private String sex;
    private String qq;
    private String tel;
    private String degree;
    private String introduce;
}
