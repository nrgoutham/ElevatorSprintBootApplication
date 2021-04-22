package com.elevator.ElevatorSpringBootApplication.service.iface;

import com.elevator.ElevatorSpringBootApplication.model.Elevator;

import java.util.List;
import java.util.UUID;

public interface ElevatorService {

    Elevator createElevator(Elevator elevator);

    Elevator updateElevator(UUID identifier, Elevator elevator);

    void removeElevator(UUID identifier);

    Elevator getElevatorById(UUID identifier);

    List<Elevator> getAllElevators();

    List<Elevator> findElevatorsByIds(List<UUID> identifiers);

}
