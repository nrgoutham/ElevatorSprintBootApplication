package com.elevator.ElevatorSpringBootApplication.dao;

import com.elevator.ElevatorSpringBootApplication.model.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ElevatorDao extends JpaRepository<Elevator, UUID> {

    @Override
    List<Elevator> findAllById(Iterable<UUID> iterable);
}
