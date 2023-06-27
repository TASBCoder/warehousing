package com.work.warehouse.mapper;

import com.work.warehouse.entity.Ledger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedgerMapper {
    int InWarehousing(Ledger ledger);
    int OutWarehousing(Ledger ledger);
}
