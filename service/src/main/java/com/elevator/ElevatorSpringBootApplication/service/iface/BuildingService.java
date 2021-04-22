package com.elevator.ElevatorSpringBootApplication.service.iface;

import com.elevator.ElevatorSpringBootApplication.model.Building;

import java.util.List;
import java.util.UUID;

public interface BuildingService {

    Building createBuilding(Building building);

    Building updateBuilding(UUID identifier, Building building);

    void removeBuilding(UUID identifier);

    Building getBuildingById(UUID identifier);

    List<Building> getAllBuildings();

    List<Building> findBuildingsByIds(List<UUID> identifiers);

}
