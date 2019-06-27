package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.entity.RouteEntity;
import com.radoapx.transitmanagementsystem.entity.StationEntity;
import com.radoapx.transitmanagementsystem.entity.StationRouteEntity;
import com.radoapx.transitmanagementsystem.jpa.RouteJPA;
import com.radoapx.transitmanagementsystem.jpa.SRJPA;
import com.radoapx.transitmanagementsystem.jpa.StationJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(path = "/get")
public class SRController {

    @Autowired
    private SRJPA srjpa;
    @Autowired
    private StationJPA stationJPA;
    @Autowired
    private RouteJPA routeJPA;

    @RequestMapping(path = "/getallsr")
    public List<StationRouteEntity> getAllFromSR(){
        return srjpa.findAll();
    }

    @Deprecated
    @RequestMapping(path = "/getstraightsolution")
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

    @RequestMapping(path = "/addsr")
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

    @RequestMapping(path = "/getbestsolution")
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

    @RequestMapping(path = "/getspecroute")
    public int[] getSpecRoute(
            @RequestParam("rid") long rouID){
        return srjpa.getSpecRoute(rouID);
    }



}
