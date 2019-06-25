package com.radoapx.transitmanagementsystem.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "station")
public class StationEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "station_id")
    private long stationId;

    @Column(name = "name")
    private String stationName;

    @Column(name = "remark")
    private String stationRemark;

    @Column(name = "loc_x")
    private String stationLoc_x;

    @Column(name = "loc_y")
    private String stationLoc_y;

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationRemark() {
        return stationRemark;
    }

    public void setStationRemark(String stationRemark) {
        this.stationRemark = stationRemark;
    }

    public String getStationLoc_x() {
        return stationLoc_x;
    }

    public void setStationLoc_x(String stationLoc_x) {
        this.stationLoc_x = stationLoc_x;
    }

    public String getStationLoc_y() {
        return stationLoc_y;
    }

    public void setStationLoc_y(String stationLoc_y) {
        this.stationLoc_y = stationLoc_y;
    }
}
