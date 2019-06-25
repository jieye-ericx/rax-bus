package com.radoapx.transitmanagementsystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "station_route")
public class StationRouteEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long SRid;

    @Column(name = "station_id")
    private long SRstationId;

    @Column(name = "route_id")
    private long SRrouteId;

    @Column(name = "remark")
    private String SRremark;

    @Column(name = "position")
    private int SRposition;

    public long getSRid() {
        return SRid;
    }

    public void setSRid(long SRid) {
        this.SRid = SRid;
    }

    public long getSRstationId() {
        return SRstationId;
    }

    public void setSRstationId(long SRstationId) {
        this.SRstationId = SRstationId;
    }

    public long getSRrouteId() {
        return SRrouteId;
    }

    public void setSRrouteId(long SRrouteId) {
        this.SRrouteId = SRrouteId;
    }

    public String getSRremark() {
        return SRremark;
    }

    public void setSRremark(String SRremark) {
        this.SRremark = SRremark;
    }

    public int getSRposition() {
        return SRposition;
    }

    public void setSRposition(int SRposition) {
        this.SRposition = SRposition;
    }
}
