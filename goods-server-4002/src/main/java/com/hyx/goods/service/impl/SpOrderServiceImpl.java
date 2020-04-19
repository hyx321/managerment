package com.hyx.goods.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.SpOrder;
import com.hyx.goods.mapper.SpOrderMapper;
import com.hyx.goods.service.SpOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *]
 * @author xiaolang
 * @since 2020-04-17
 */
@Service
public class SpOrderServiceImpl extends ServiceImpl<SpOrderMapper, SpOrder> implements SpOrderService {

    @Resource
    private SpOrderMapper spOrderMapper;

    @Override
    public CommonResult getOrderList(int current, int size) {
        Page<SpOrder> page = new Page<>(current,size);
        spOrderMapper.selectPage(page,null);
        List<SpOrder> temp = page.getRecords();
        page.getTotal();
        if(temp != null){
            return new CommonResult<>(200,"订单列表信息获取成功",temp);
        }else{
            return new CommonResult<>(404,"订单列表信息获取失败",temp);
        }
    }
}
