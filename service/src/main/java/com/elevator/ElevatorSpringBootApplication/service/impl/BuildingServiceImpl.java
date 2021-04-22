package com.elevator.ElevatorSpringBootApplication.service.impl;

import com.elevator.ElevatorSpringBootApplication.dao.BuildingDao;
import com.elevator.ElevatorSpringBootApplication.model.Building;
import com.elevator.ElevatorSpringBootApplication.service.iface.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuildingServiceImpl implements BuildingService {

    final BuildingDao buildingDao;

    @Autowired
    public BuildingServiceImpl(final BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    @Override
    public Building createBuilding(final Building elevator) {
        final Building savedBuilding;
        try {
            savedBuilding = buildingDao.saveAndFlush(elevator);
        } catch (final JpaSystemException jse) {
            return null;
        }
        return savedBuilding;
    }

    @Override
    public Building updateBuilding(final UUID identifier, final Building building) {
        Building updatedBuilding = null;
        final Building buildingInDb = getBuildingById(identifier);
        if (buildingInDb != null) {
            buildingInDb.setName(building.getName());
            buildingInDb.setLocation(building.getLocation());
            buildingInDb.setElevatorIds(building.getElevatorIds());
            updatedBuilding = createBuilding(buildingInDb);
        }
        return updatedBuilding;
    }

    @Override
    public void removeBuilding(final UUID identifier) {
        buildingDao.deleteById(identifier);
    }

    @Override
    public Building getBuildingById(final UUID identifier) {
        final Optional<Building> buildingInDb = buildingDao.findById(identifier);
        return buildingInDb.orElse(null);
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingDao.findAll();
    }

    @Override
    public List<Building> findBuildingsByIds(final List<UUID> identifiers) {
        return buildingDao.findAllById(identifiers);
    }
}
