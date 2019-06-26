package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.entity.StationEntity;
import com.radoapx.transitmanagementsystem.jpa.StationJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/sta")
public class StationController {
    @Autowired
    private StationJPA stationJPA;

    @RequestMapping(path = "/getallpoints")
    public List<StationEntity> getALLPoints(){
        return stationJPA.findAll();
    }

    @RequestMapping(path = "/getS")
    public long getS(@RequestParam("name") String name){
        return stationJPA.searchStaIdByStaName(name);
    }

    @RequestMapping(path = "/addsta")
    public StationEntity addStation(
            @RequestParam(value = "name")String name,
            @RequestParam(value = "x")String x,
            @RequestParam(value = "y")String y,
            @RequestParam(value = "remark",defaultValue = "")String remark){

        StationEntity stationEntity=new StationEntity();
//        stationEntity.setStationId(15);
        stationEntity.setStationLoc_x(x);
        stationEntity.setStationLoc_y(y);
        stationEntity.setStationName(name);
        stationEntity.setStationRemark(remark);
        stationJPA.save(stationEntity);
        return stationEntity;
    }
}
