package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.jpa.StationJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {
    @Autowired
    private StationJPA stationJPA;
}
