package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.entity.RouteEntity;
import com.radoapx.transitmanagementsystem.entity.StationEntity;
import com.radoapx.transitmanagementsystem.entity.StationRouteEntity;
import com.radoapx.transitmanagementsystem.jpa.RouteJPA;
import com.radoapx.transitmanagementsystem.jpa.SRJPA;
import com.radoapx.transitmanagementsystem.jpa.StationJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "主要操作路线与站点相关的控制器")
@CrossOrigin
@RestController
@RequestMapping(path = "/get")
public class SRController {

    @Autowired
    private SRJPA srjpa;
    @Autowired
    private StationJPA stationJPA;
    @Autowired
    private RouteJPA routeJPA;

    @ApiOperation(value = "获得所有路线站点信息")
    @RequestMapping(path = "/getallsr",method = RequestMethod.GET)
    public List<StationRouteEntity> getAllFromSR(){
        return srjpa.findAll();
    }

    @Deprecated
    @RequestMapping(path = "/getstraightsolution",method = RequestMethod.POST)
    public HashMap getStraightSolution(
            @RequestParam("st_sta") String startStation,
            @RequestParam("end_sta") String endStation){
        List<StationRouteEntity> stationRouteEntities=srjpa.findAll();
        long startId=stationJPA.searchStaIdByStaName(startStation);
        long endId=stationJPA.searchStaIdByStaName(endStation);
        boolean flag=false;
        for(StationRouteEntity e:stationRouteEntities){
            for(StationRouteEntity in:stationRouteEntities ){
                if(e.getSRrouteId()==in.getSRrouteId()){
                    int staPos=0;
                    int endPos=0;
                    if(e.getSRstationId()==startId&&in.getSRstationId()==endId){
                        staPos=e.getSRposition();
                        endPos=in.getSRposition();
                        flag=true;
                    }else if(e.getSRstationId()==endId&&in.getSRstationId()==startId){
                        staPos=in.getSRposition();
                        endPos=e.getSRposition();
                        flag=true;
                    }
                    if(flag){
                        List<Long> longs=stationJPA.searchStasByRouteAndPosition(e.getSRrouteId(),Math.max(staPos, endPos),Math.min(staPos,endPos));
//                        Arrays.sort(longs);
                        if(staPos>endPos){
                            Collections.reverse(longs);
                        }
                        RouteEntity routeEntity1=routeJPA.findById(e.getSRrouteId()).get();
                        HashMap hashMap=new HashMap();
                        hashMap.put("routeObj", routeEntity1);
                        hashMap.put("staIds",longs);
                        return hashMap;
                    }
                }
            }
        }
        return null;
    }

    @ApiOperation(value = "增加路线站点绑定信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="post", name = "rid", value = "路线id", required = true, dataType = "long"),
            @ApiImplicitParam(paramType="post", name = "sid", value = "车站id", required = true, dataType = "long"),
            @ApiImplicitParam(paramType="post", name = "position", value = "路线中车站位置", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "post",name = "remark",value = "路线备注",required = false,defaultValue = "null",dataType = "String")
    })
    @RequestMapping(path = "/addsr",method = RequestMethod.POST)
    public StationRouteEntity addSR(
            @RequestParam("rid") long routeId,
            @RequestParam("sid") long stationId,
            @RequestParam("position") int position ,
            @RequestParam(value = "remark",defaultValue = "") String remark
            ){
        StationRouteEntity stationRouteEntity=new StationRouteEntity();
        stationRouteEntity.setSRrouteId(routeId);
        stationRouteEntity.setSRstationId(stationId);
        stationRouteEntity.setSRposition(position);
        stationRouteEntity.setSRremark(remark);
        srjpa.save(stationRouteEntity);
        return stationRouteEntity;
    }

    @ApiOperation(value = "输入起点站终点站获得路径")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="post", name = "st_sta", value = "起点站名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="post", name = "end_sta", value = "终点站名称", required = true, dataType = "String"),
    })
    @RequestMapping(path = "/getbestsolution",method = RequestMethod.POST)
    public HashMap getBestSolution(
            @RequestParam("st_sta") String startStation,
            @RequestParam("end_sta") String endStation
    ){
        long startId=stationJPA.searchStaIdByStaName(startStation);
        long endId=stationJPA.searchStaIdByStaName(endStation);
        long[][] zero=srjpa.getTransitStraight(startId, endId);
        long[][] once=srjpa.getTransitOnce(startId, endId);
        long[][] twice=srjpa.getTransitTwice(startId, endId);
        if(0==zero.length&&0==once.length&&0==twice.length) return null;

        HashMap hashMap=new HashMap();
        if(0!=zero.length) hashMap.put("zero", zero[0]);
        if(0!=once.length) hashMap.put("once",once[0] );
        if(0!=twice.length) hashMap.put("twice", twice[0]);
        return hashMap;

//        return zero.length+"/"+once.length+"/"+twice.length;
    }

    @ApiOperation(value = "输入路线id获得其经过的车站id")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="post", name = "rid", value = "路线id", required = true, dataType = "long"),
    })
    @RequestMapping(path = "/getspecroute",method = RequestMethod.POST)
    public int[] getSpecRoute(
            @RequestParam("rid") long rouID){
        return srjpa.getSpecRoute(rouID);
    }



}
