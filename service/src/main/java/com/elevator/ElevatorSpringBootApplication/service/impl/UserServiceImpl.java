package com.elevator.ElevatorSpringBootApplication.service.impl;

import com.elevator.ElevatorSpringBootApplication.dao.UserDao;
import com.elevator.ElevatorSpringBootApplication.dto.SelectFloorInElevatorForUserResponse;
import com.elevator.ElevatorSpringBootApplication.dto.StatusOfAllElevatorInABuildingForUserResponse;
import com.elevator.ElevatorSpringBootApplication.dto.SummonElevatorForUserResponse;
import com.elevator.ElevatorSpringBootApplication.dto.UserWithBuildingsResponse;
import com.elevator.ElevatorSpringBootApplication.model.Building;
import com.elevator.ElevatorSpringBootApplication.model.Elevator;
import com.elevator.ElevatorSpringBootApplication.model.Status;
import com.elevator.ElevatorSpringBootApplication.model.User;
import com.elevator.ElevatorSpringBootApplication.service.iface.BuildingService;
import com.elevator.ElevatorSpringBootApplication.service.iface.ElevatorService;
import com.elevator.ElevatorSpringBootApplication.service.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    final UserDao userDao;
    final ElevatorService elevatorService;
    final BuildingService buildingService;

    @Autowired
    public UserServiceImpl(final UserDao userDao, final BuildingService buildingService, final ElevatorService elevatorService) {
        this.userDao = userDao;
        this.buildingService = buildingService;
        this.elevatorService = elevatorService;
    }


    @Override
    public User createUser(final User user) {
        final User savedUser;
        try {
            savedUser = userDao.saveAndFlush(user);
        } catch (final JpaSystemException jse) {
            return null;
        }
        return savedUser;
    }

    @Override
    public User updateUser(final UUID identifier, final User user) {
        User updatedUser = null;
        final User userInDb = userDao.findUserByIdentifier(identifier);
        if (userInDb != null) {
            userInDb.setName(user.getName());
            userInDb.setBuildingIds(user.getBuildingIds());
            updatedUser = createUser(userInDb);
        }
        return updatedUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public UserWithBuildingsResponse getBuildingsByUserIdentifier(final UUID userIdentifier) {
        final UserWithBuildingsResponse userWithBuildingsResponse = UserWithBuildingsResponse.builder().build();
        final Optional<User> userInDb = userDao.findById(userIdentifier);
        if (userInDb.isPresent()) {
            userWithBuildingsResponse.setUser(userInDb.get());
            userWithBuildingsResponse.setBuildings(buildingService.findBuildingsByIds(userInDb.get().getBuildingIds()));
        }
        return userWithBuildingsResponse;
    }

    @Override
    public StatusOfAllElevatorInABuildingForUserResponse getStatusOfAllElevatorInABuildingForUser(final UUID userIdentifier, final UUID buildingIdentifier) {
        final StatusOfAllElevatorInABuildingForUserResponse response = StatusOfAllElevatorInABuildingForUserResponse.builder()
                .userIdentifier(userIdentifier).buildingIdentifier(buildingIdentifier).build();
        final Optional<User> userInDb = userDao.findById(userIdentifier);
        if (userInDb.isEmpty()) {
            response.setMessage("Could Not find User with given userIdentifier");
            return response;
        }
        final Building building = buildingService.getBuildingById(buildingIdentifier);
        if (!userInDb.get().getBuildingIds().contains(buildingIdentifier) || building == null) {
            response.setMessage("Could Not find building for User with given buildingIdentifier");
            return response;
        }
        final List<Elevator> elevators = elevatorService.findElevatorsByIds(building.getElevatorIds());
        response.setElevators(elevators);
        return response;
    }

    @Override
    public SummonElevatorForUserResponse summonElevatorForUser(final UUID userIdentifier, final UUID buildingIdentifier) {
        final SummonElevatorForUserResponse summonElevatorForUserResponse = SummonElevatorForUserResponse.builder().build();
        final Optional<User> userInDb = userDao.findById(userIdentifier);
        if (userInDb.isEmpty()) {
            summonElevatorForUserResponse.setMessage("Could Not find User with given userIdentifier");
            return summonElevatorForUserResponse;
        }
        final Building building = buildingService.getBuildingById(buildingIdentifier);
        if (!userInDb.get().getBuildingIds().contains(buildingIdentifier) || building == null) {
            summonElevatorForUserResponse.setMessage("Could Not find building for User with given buildingIdentifier");
            return summonElevatorForUserResponse;
        }
        final List<Elevator> elevators = elevatorService.findElevatorsByIds(building.getElevatorIds());
        final List<Elevator> fetchElevatorWhichIsFree = elevators.stream().filter(elevator -> elevator.getState().equals(Status.STOPPED)).collect(Collectors.toList());
        if (fetchElevatorWhichIsFree.isEmpty()) {
            summonElevatorForUserResponse.setMessage("All the elevators are currently busy ,please wait");
            return summonElevatorForUserResponse;
        }
        summonElevatorForUserResponse.setUser(userInDb.get());
        summonElevatorForUserResponse.setElevator(fetchElevatorWhichIsFree.get(0));
        summonElevatorForUserResponse.setMessage("Your elevator is ready");
        return summonElevatorForUserResponse;
    }

    @Override
    public SelectFloorInElevatorForUserResponse selectFloorInElevatorForUser(final UUID userIdentifier, final UUID elevatorIdentifier, final int selectedFloor) throws InterruptedException {
        final SelectFloorInElevatorForUserResponse selectFloorInElevatorForUserResponse = SelectFloorInElevatorForUserResponse.builder().build();
        final Optional<User> userInDb = userDao.findById(userIdentifier);
        if (userInDb.isEmpty()) {
            selectFloorInElevatorForUserResponse.setMessage("Could Not find User with given userIdentifier");
            return selectFloorInElevatorForUserResponse;
        }
        final Elevator elevator = elevatorService.getElevatorById(elevatorIdentifier);
        if (elevator == null) {
            selectFloorInElevatorForUserResponse.setMessage("selected elevator is not found/Out of service");
            return selectFloorInElevatorForUserResponse;
        }
        if (!elevator.getTotalFloors().contains(selectedFloor)) {
            selectFloorInElevatorForUserResponse.setMessage("This elevator is not in service for selected Floor,Please select appropriate floor");
            return selectFloorInElevatorForUserResponse;
        }
        if (elevator.getCurrentFloor().equals(selectedFloor)) {
            selectFloorInElevatorForUserResponse.setMessage("selected Floor is same as currentFloor,Please select appropriate floor");
            return selectFloorInElevatorForUserResponse;
        }
        if (selectedFloor > elevator.getCurrentFloor()) {
            // user selected to go UP
            elevator.setState(Status.UP);
            elevatorService.updateElevator(elevatorIdentifier, elevator);
            // Sleep for 2 seconds to simulate elevator is moving UP
            Thread.sleep(2000);
        } else {
            // user selected to go DOWN
            elevator.setState(Status.DOWN);
            elevatorService.updateElevator(elevatorIdentifier, elevator);
            // Sleep for 2 seconds to simulate elevator is moving DOWN
            Thread.sleep(2000);
        }
        // finally set elevator status back to Stopped so that it can be used by other users
        elevator.setState(Status.STOPPED);
        elevator.setCurrentFloor(selectedFloor);
        elevatorService.updateElevator(elevatorIdentifier, elevator);

        selectFloorInElevatorForUserResponse.setUser(userInDb.get());
        selectFloorInElevatorForUserResponse.setElevator(elevator);
        selectFloorInElevatorForUserResponse.setMessage(String.format("You have reached selected Floor %s", selectedFloor));
        // Return response back to User
        return selectFloorInElevatorForUserResponse;
    }

}
