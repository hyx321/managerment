package com.hyx.miaosha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/30 16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KillRequest {
    private int UserId;
    private int ItemId;
}
