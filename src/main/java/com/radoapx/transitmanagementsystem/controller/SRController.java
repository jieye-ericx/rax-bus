package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.entity.StationRouteEntity;
import com.radoapx.transitmanagementsystem.jpa.SRJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/get")
public class SRController {

    @Autowired
    private SRJPA srjpa;

    @RequestMapping(path = "/getallsr")
    public List<StationRouteEntity> getAllFromSR(){
        return srjpa.findAll();
    }

//    @RequestMapping(path = "/getallpoints")
//    public
}
