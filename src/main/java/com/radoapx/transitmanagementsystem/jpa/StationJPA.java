package com.radoapx.transitmanagementsystem.jpa;

import com.radoapx.transitmanagementsystem.entity.StationEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationJPA extends JpaRepository<StationEntity,Long> {

}
