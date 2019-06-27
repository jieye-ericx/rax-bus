package com.radoapx.transitmanagementsystem.jpa;

import com.radoapx.transitmanagementsystem.entity.StationRouteEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SRJPA extends JpaRepository<StationRouteEntity,Long> {

    /**
     * 0 route 1 startSta 2 endSta 3 pos
     * @param id1
     * @param id2
     * @return
     */
    @Query(value = "select * from straight_way " +
            "where startSta=?1 and endSta=?2 " +
            "order by pos;",nativeQuery = true)
    long[][] getTransitStraight(long id1,long id2);


    /**
     * 0 startSta 1 route0 2 posNeed0 3 transferSta0
     * 4 route1 5 posNeed1 6 endSta
     * @param id1
     * @param id2
     * @return
     */
    @Query(value = "select * from transfer_once " +
            "where startSta=?1 and endSta=?2 " +
            "order by posNeed0,posNeed1;",nativeQuery = true)
    long[][] getTransitOnce(long id1,long id2);

    /**
     * 0 startSta 1 route0 2 posNeed0 3 transferSta0
     * 4 route1 5 posNeed1 6 transferSta1 7 route2
     * 8 posNeed2 9 endSta
     * @param id1
     * @param id2
     * @return
     */
    @Query(value = "select * from transfer_twice " +
            "where startSta=?1 and endSta=?2 " +
            "order by posNeed0,posNeed1,posNeed2;",nativeQuery = true)
    long[][] getTransitTwice(long id1,long id2);

    @Query(value = "select station_id from station_route where route_id=?1 " +
            "order by position",nativeQuery = true)
    int[] getSpecRoute(long rid);


}
