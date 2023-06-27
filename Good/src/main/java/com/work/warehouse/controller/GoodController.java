package com.work.warehouse.controller;


import com.work.warehouse.config.ResultReturn;
import com.work.warehouse.entity.Good;
import com.work.warehouse.service.GoodService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/good")
@Log4j
public class GoodController {
    @Autowired
    private GoodService goodService;

    @RequestMapping(value = "/addGood/{identify}/{name}/{price}/{stock}/{description}")
    public ResultReturn addGood(@PathVariable(required = false) String identify,
                                @PathVariable String name,
                                @PathVariable Float price,
                                @PathVariable Integer stock,
                                @PathVariable String description){
        Good result = new Good();
        if (identify == null) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            result.setIdentify(uuid);
        } else {
            result.setIdentify(identify);
        }
        result.setName(name);
        result.setPrice(price);
        result.setStock(stock);
        result.setDescription(description);
        return goodService.addGood(result);
    }


    @RequestMapping("/updateGoodByIdentify")
    public ResultReturn updateGoodByIdentify(@RequestBody Good good){
        return goodService.updateGoodByIdentify(good);
    }

    @RequestMapping("/deleteGood/{identify}")
    public ResultReturn deleteGood(@PathVariable("identify") String identify){
        return goodService.deleteGood(identify);
    }

    @RequestMapping("/selectGoodByName/{name}")
    public ResultReturn selectGoodByName(@PathVariable("name") String name){
        return goodService.selectGoodByName(name);
    }

    @RequestMapping("/selectGoodLimitByName/{name}/{current}/{count}")
    public ResultReturn selectGoodLimitByName(@PathVariable("name") String name, @PathVariable("current") Integer current, @PathVariable("count") Integer count){
        return goodService.selectGoodLimitByName(name, current, count);
    }

    @RequestMapping("/selectGoodByIdentify/{identify}")
    public ResultReturn selectGoodByIdentify(@PathVariable("name") String identify){
        return goodService.selectGoodByName(identify);
    }
}
