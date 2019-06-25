package com.radoapx.transitmanagementsystem.controller;

import com.radoapx.transitmanagementsystem.jpa.RouteJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    @Autowired
    private RouteJPA routeJPA;
}
