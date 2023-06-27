package com.work.warehouse.service;

import com.work.warehouse.config.ResultReturn;
import com.work.warehouse.entity.Good;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface GoodService {
    ResultReturn addGood(Good good);

    ResultReturn updateGoodByIdentify(Good good);

    ResultReturn deleteGood(String identify);

    ResultReturn selectGoodByName(String name);

    ResultReturn selectGoodLimitByName(String name, Integer pageSize, Integer count);

    ResultReturn selectGoodByIdentify(String identify);
}
