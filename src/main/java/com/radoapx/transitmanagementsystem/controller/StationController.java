package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.entity.StationEntity;
import com.radoapx.transitmanagementsystem.jpa.StationJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
@RequestMapping(path = "/sta")
public class StationController {
    @Autowired
    private StationJPA stationJPA;

    @ApiOperation(value = "获得所有车站对象")
    @RequestMapping(path = "/getallstations")
    public List<StationEntity> getALLPoints(){
        return stationJPA.findAll();
    }

    @ApiOperation(value = "通过名称获得车站对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "车站名", required = true, dataType = "String")
    })
    @RequestMapping(path = "/getonestationbyname")
    public long getS(@RequestParam("name") String name){
        return stationJPA.searchStaIdByStaName(name);
    }


    @ApiOperation(value = "添加车站")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "车站名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "x", value = "坐标x", required = true, dataType = "String"),
            @ApiImplicitParam(name = "y", value = "坐标y", required = true, dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", required = true, dataType = "String")
    })
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

    @ApiOperation(value = "根据车站id删除数据",httpMethod = "Post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "车站id", required = true, dataType = "long")
    })
    @RequestMapping(path = "/deletesta")
    public void deleteStation(
            @RequestParam(value = "id") long id
    ){
        stationJPA.deleteById(id);
    }
}
