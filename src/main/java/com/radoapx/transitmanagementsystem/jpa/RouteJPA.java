package com.radoapx.transitmanagementsystem.jpa;

import com.radoapx.transitmanagementsystem.entity.RouteEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteJPA extends JpaRepository<RouteEntity,Long> {

}
