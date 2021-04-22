package com.elevator.ElevatorSpringBootApplication.service.impl;

import com.elevator.ElevatorSpringBootApplication.dao.ElevatorDao;
import com.elevator.ElevatorSpringBootApplication.model.Elevator;
import com.elevator.ElevatorSpringBootApplication.service.iface.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ElevatorServiceImpl implements ElevatorService {

    final ElevatorDao elevatorDao;

    @Autowired
    public ElevatorServiceImpl(final ElevatorDao elevatorDao) {
        this.elevatorDao = elevatorDao;
    }

    @Override
    public Elevator createElevator(final Elevator elevator) {
        final Elevator savedElevator;
        try {
            savedElevator = elevatorDao.save(elevator);
        } catch (final JpaSystemException jse) {
            return null;
        }
        return savedElevator;
    }

    @Override
    public Elevator updateElevator(final UUID identifier, final Elevator elevator) {
        Elevator updatedElevator = null;
        final Elevator elevatorInDb = getElevatorById(identifier);
        if (elevatorInDb != null) {
            elevatorInDb.setName(elevator.getName());
            elevatorInDb.setCurrentFloor(elevator.getCurrentFloor());
            elevatorInDb.setTotalFloors(elevator.getTotalFloors());
            elevatorInDb.setState(elevator.getState());
            updatedElevator = createElevator(elevatorInDb);
        }
        return updatedElevator;
    }

    @Override
    public void removeElevator(final UUID identifier) {
        elevatorDao.deleteById(identifier);
    }

    @Override
    public Elevator getElevatorById(final UUID identifier) {
        final Optional<Elevator> elevatorInDb = elevatorDao.findById(identifier);
        return elevatorInDb.orElse(null);
    }

    @Override
    public List<Elevator> getAllElevators() {
        return elevatorDao.findAll();
    }

    @Override
    public List<Elevator> findElevatorsByIds(final List<UUID> identifiers) {
        return elevatorDao.findAllById(identifiers);
    }
}
