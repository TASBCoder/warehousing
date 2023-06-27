package com.work.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ledger {
    private Integer id;
    private String goodName;
    private String goodIdentify;
    private String serialNumber;
    private Integer number;
    private String store;
    private String operatorId;
    private Date createTime;
    private Integer operator;
}
