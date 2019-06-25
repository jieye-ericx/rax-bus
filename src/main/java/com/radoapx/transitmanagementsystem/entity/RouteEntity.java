package com.radoapx.transitmanagementsystem.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class RouteEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "route_id")
    private long routeId;

    @Column(name = "name")
    private String routeName;

    @Column(name = "remark")
    private String routeRemark;

    @Column(name = "start_time")
    private String routeStartTime;

    @Column(name = "end_time")
    private String routeEndTime;

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteRemark() {
        return routeRemark;
    }

    public void setRouteRemark(String routeRemark) {
        this.routeRemark = routeRemark;
    }

    public String getRouteStartTime() {
        return routeStartTime;
    }

    public void setRouteStartTime(String routeStartTime) {
        this.routeStartTime = routeStartTime;
    }

    public String getRouteEndTime() {
        return routeEndTime;
    }

    public void setRouteEndTime(String routeEndTime) {
        this.routeEndTime = routeEndTime;
    }
}
