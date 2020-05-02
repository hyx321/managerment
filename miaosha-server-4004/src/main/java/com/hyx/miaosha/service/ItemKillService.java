package com.hyx.miaosha.service;

import com.hyx.common.entities.CommonResult;
import com.hyx.miaosha.entities.ItemKill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyx.miaosha.entities.KillRequest;

/**
 * <p>
 * 待秒杀商品表 服务类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
public interface ItemKillService extends IService<ItemKill> {
    CommonResult getMiaoshaList();

    CommonResult getMiaosha(KillRequest killRequest) throws Exception;
}
