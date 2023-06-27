package com.work.warehouse.service.impl;

import com.work.warehouse.config.ResultReturn;
import com.work.warehouse.entity.Good;
import com.work.warehouse.mapper.GoodMapper;
import com.work.warehouse.service.GoodService;
import com.work.warehouse.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodMapper goodMapper;

    @Override
    public ResultReturn addGood(Good good) {
        int i = goodMapper.addGood(good);
        if ( i== 1){
            return ResultReturn.success();
        }
        return ResultReturn.fail("插入失败");
    }

    @Override
    public ResultReturn updateGoodByIdentify(Good good) {
        int i = goodMapper.updateGoodByIdentify(good);

        if ( i== 1){
            return ResultReturn.success();
        }
        return ResultReturn.fail("更新失败");
    }

    @Override
    public ResultReturn deleteGood(String identify) {
        int i = goodMapper.deleteGood(identify);
        if ( i== 1){
            return ResultReturn.success();
        }
        return ResultReturn.fail("删除失败");
    }

    @Override
    public ResultReturn selectGoodByName(String name) {
        Good good = goodMapper.selectGoodByName(name);
        if (good != null) {
            return ResultReturn.success(good);
        }
        return ResultReturn.fail("查询单个失败");
    }

    @Override
    public ResultReturn selectGoodLimitByName(String name, Integer current, Integer count) {
        List<Good> goods = goodMapper.selectGoodLimitByName(name, current, count);
        System.out.println(goods);
        if (goods.isEmpty()) {
            return ResultReturn.fail("查询失败");
        }
        int total = goodMapper.selectTotalGoodByName(name);
        PageVO pageVO = PageVO.getPageVO(current, count, total, goods);
        return ResultReturn.success(pageVO);
    }

    @Override
    public ResultReturn selectGoodByIdentify(String identify) {
        Good good = goodMapper.selectGoodByIdentify(identify);
        if (good == null) {
            return ResultReturn.fail("查询失败");
        }
        return ResultReturn.success(good);
    }
}
