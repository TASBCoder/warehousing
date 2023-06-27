package com.work.warehouse.mapper;

import com.work.warehouse.config.ResultReturn;
import com.work.warehouse.entity.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodMapper {
    int addGood(Good good);

    int updateGoodByIdentify(Good good);

    int deleteGood(String identify);

    Good selectGoodByName(String name);

    List<Good> selectGoodLimitByName(String name, Integer current, Integer count);

    Good selectGoodByIdentify(String identify);

    int selectTotalGoodByName(String name);
}
