package com.work.warehouse.controller;

import com.work.warehouse.config.ResultReturn;
import com.work.warehouse.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ledger")
public class LedgerController {
    @Autowired
    private LedgerService ledgerService;

    @RequestMapping("/inWarehousing/{goodIdentify}/{goodName}/{price}/{number}/{description}/store/operatorId")
    public ResultReturn InWarehousing(String goodIdentify,
                                      String goodName,
                                      Float price,
                                      Integer number,
                                      String description,
                                      String store,
                                      String operatorId) {
        return ledgerService.InWarehousing(goodIdentify, goodName, price, number, description, store, operatorId);
    }

    @RequestMapping("/outWarehousing/{goodIdentify}/{number}/store/operatorId")
    public ResultReturn OutWarehousing(String goodIdentify, Integer number, String store, String operatorId) {
        return ledgerService.OutWarehousing(goodIdentify, number, store, operatorId);
    }

    @RequestMapping("/selectInWarehouseOrder/{goodName}/{createTime}/pageSize/operatorId")
    public ResultReturn SelectInWarehouseOrder(String goodIdentify, Integer number, String store, String operatorId) {
        return ledgerService.OutWarehousing(goodIdentify, number, store, operatorId);
    }
}
