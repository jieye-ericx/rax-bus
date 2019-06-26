package com.radoapx.transitmanagementsystem.jpa;

import com.radoapx.transitmanagementsystem.entity.StationEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.constraints.Max;

public interface StationJPA extends JpaRepository<StationEntity,Long> {

    @Query(value = "select station_id from station s where s.name=?1",nativeQuery = true)
    long searchStaIdByStaName(String name);

    @Query(value = "select sr.station_id from station_route sr" +
            " where sr.route_id=?1 and sr.position<=?2 and sr.position>=?3 " +
            "order by position ",nativeQuery = true)
    List<Long> searchStasByRouteAndPosition(Long id,int p1,int p2);


}
