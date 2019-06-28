package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.entity.RouteEntity;
import com.radoapx.transitmanagementsystem.jpa.RouteJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import sun.awt.RepaintArea;

@CrossOrigin
@RestController
@RequestMapping(path = "/rou")
public class RouteController {

    @Autowired
    private RouteJPA routeJPA;

    @RequestMapping(path = "/addrou")
    public RouteEntity addRoute(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "remark",defaultValue = "") String remark,
            @RequestParam(value = "sttime") String startTime,
            @RequestParam(value = "endtime") String endTime
    ){
        RouteEntity routeEntity=new RouteEntity();
        routeEntity.setRouteName(name);
        routeEntity.setRouteRemark(remark);
        routeEntity.setRouteStartTime(startTime);
        routeEntity.setRouteEndTime(endTime);
        routeJPA.save(routeEntity);
        return routeEntity;
    }

    @RequestMapping(path = "/getallroutes")
    public List<RouteEntity> getAllRoute(){
        return routeJPA.findAll();
    }
}
