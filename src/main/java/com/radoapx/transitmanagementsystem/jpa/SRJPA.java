package com.radoapx.transitmanagementsystem.jpa;

import com.radoapx.transitmanagementsystem.entity.StationRouteEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SRJPA extends JpaRepository<StationRouteEntity,Long> {
}
