package com.elevator.ElevatorSpringBootApplication.dao;

import com.elevator.ElevatorSpringBootApplication.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BuildingDao extends JpaRepository<Building, UUID> {

    @Override
    List<Building> findAllById(Iterable<UUID> iterable);
}
