package com.hyx.goods.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.SpOrder;
import com.hyx.goods.entity.SpGoods;
import com.hyx.goods.mapper.SpGoodsMapper;
import com.hyx.goods.service.SpGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-17
 */
@Service
public class SpGoodsServiceImpl extends ServiceImpl<SpGoodsMapper, SpGoods> implements SpGoodsService {

    @Resource
    private SpGoodsMapper selectPage;

    @Override
    public CommonResult getGoodsList(int current, int size) {
        Page<SpGoods> page = new Page<>(current,size);
        selectPage.selectPage(page,null);
        List<SpGoods> temp = page.getRecords();
        page.getTotal();
        if(temp != null){
            return new CommonResult<>(200,"商品列表信息获取成功",temp);
        }else{
            return new CommonResult<>(404,"商品列表信息获取失败",temp);
        }
    }

}
