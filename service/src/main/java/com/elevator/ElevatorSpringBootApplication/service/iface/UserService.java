package com.elevator.ElevatorSpringBootApplication.service.iface;

import com.elevator.ElevatorSpringBootApplication.dto.SelectFloorInElevatorForUserResponse;
import com.elevator.ElevatorSpringBootApplication.dto.StatusOfAllElevatorInABuildingForUserResponse;
import com.elevator.ElevatorSpringBootApplication.dto.SummonElevatorForUserResponse;
import com.elevator.ElevatorSpringBootApplication.dto.UserWithBuildingsResponse;
import com.elevator.ElevatorSpringBootApplication.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(User user);

    User updateUser(UUID identifier, User user);

    List<User> getAllUsers();

    UserWithBuildingsResponse getBuildingsByUserIdentifier(UUID userIdentifier);

    StatusOfAllElevatorInABuildingForUserResponse getStatusOfAllElevatorInABuildingForUser(UUID userIdentifier, UUID buildingIdentifier);

    SummonElevatorForUserResponse summonElevatorForUser(UUID userIdentifier, UUID buildingIdentifier);

    SelectFloorInElevatorForUserResponse selectFloorInElevatorForUser(UUID userIdentifier, UUID elevatorIdentifier, int selectedFloor) throws InterruptedException;


}
