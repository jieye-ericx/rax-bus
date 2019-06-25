package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.entity.StationEntity;
import com.radoapx.transitmanagementsystem.jpa.StationJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/get")
public class StationController {
    @Autowired
    private StationJPA stationJPA;

    @RequestMapping(path = "/getallpoints")
    public List<StationEntity> getALLPoints(){
        return stationJPA.findAll();
    }
}
