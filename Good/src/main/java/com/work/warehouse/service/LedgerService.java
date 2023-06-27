package com.work.warehouse.service;

import com.work.warehouse.config.ResultReturn;

public interface LedgerService {
    ResultReturn OutWarehousing(String goodIdentify, Integer number, String store, String operatorId);

    ResultReturn InWarehousing(String goodIdentify,
                               String name,
                               Float price,
                               Integer number,
                               String description,
                               String store,
                               String operatorId);
}
