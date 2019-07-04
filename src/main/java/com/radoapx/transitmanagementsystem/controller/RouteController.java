package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.entity.RouteEntity;
import com.radoapx.transitmanagementsystem.jpa.RouteJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@CrossOrigin
@RestController
@RequestMapping(path = "/rou")
public class RouteController {

    @Autowired
    private RouteJPA routeJPA;

    @ApiOperation(value = "增加路线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "车站名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false,defaultValue = "null",dataType = "String"),
            @ApiImplicitParam(name = "sttime", value = "首班时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endtime", value = "末班时间", required = true, dataType = "String")
    })
    @RequestMapping(path = "/addrou",method = RequestMethod.POST)
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

    @ApiOperation(value = "获得所有路线")
    @RequestMapping(path = "/getallroutes",method = RequestMethod.GET)
    public List<RouteEntity> getAllRoute(){
        return routeJPA.findAll();
    }

    @ApiOperation(value = "删除路线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "车站id", required = true, dataType = "long")
    })
    @RequestMapping(path = "/deleterou")
    public void deleteRoute(
            @RequestParam(value = "id") long id
    ){
        routeJPA.deleteById(id);
    }
}
