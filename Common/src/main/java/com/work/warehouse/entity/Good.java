package com.work.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Good {

    private Integer id;
    private String identify;
    private String name;
    private Float price;
    private Integer stock;
    private Date createTime;
    private String description;
    private Integer deleted;
}
