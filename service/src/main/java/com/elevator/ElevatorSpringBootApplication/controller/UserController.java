package com.elevator.ElevatorSpringBootApplication.controller;

import com.elevator.ElevatorSpringBootApplication.dto.SelectFloorInElevatorForUserResponse;
import com.elevator.ElevatorSpringBootApplication.dto.StatusOfAllElevatorInABuildingForUserResponse;
import com.elevator.ElevatorSpringBootApplication.dto.SummonElevatorForUserResponse;
import com.elevator.ElevatorSpringBootApplication.dto.UserWithBuildingsResponse;
import com.elevator.ElevatorSpringBootApplication.model.User;
import com.elevator.ElevatorSpringBootApplication.service.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "user", method = POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        final User response = userService.createUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "user/{identifier}", method = PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> updateUser(@PathVariable("identifier") final UUID identifier, @RequestBody final User user) {
        final User response = userService.updateUser(identifier, user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "users", method = GET)
    public ResponseEntity<List<User>> findAllUsers() {
        final List<User> response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{identifier}/buildings", method = GET)
    public ResponseEntity<UserWithBuildingsResponse> findBuildingsForUser(@PathVariable("identifier") final UUID identifier) {
        final UserWithBuildingsResponse response = userService.getBuildingsByUserIdentifier(identifier);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{userIdentifier}/building/{buildingIdentifier}/elevatorStatus", method = GET)
    public ResponseEntity<StatusOfAllElevatorInABuildingForUserResponse> getStatusOfAllElevatorInABuildingForUser(@PathVariable("userIdentifier") final UUID userIdentifier,
                                                                                                                  @PathVariable("buildingIdentifier") final UUID buildingIdentifier) {
        final StatusOfAllElevatorInABuildingForUserResponse response = userService.getStatusOfAllElevatorInABuildingForUser(userIdentifier, buildingIdentifier);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{userIdentifier}/building/{buildingIdentifier}/summon", method = GET)
    public ResponseEntity<SummonElevatorForUserResponse> summonElevatorForUser(@PathVariable("userIdentifier") final UUID userIdentifier,
                                                                               @PathVariable("buildingIdentifier") final UUID buildingIdentifier) {
        final SummonElevatorForUserResponse response = userService.summonElevatorForUser(userIdentifier, buildingIdentifier);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{userIdentifier}/elevator/{elevatorIdentifier}/selectFloor/{selectedFloor}", method = GET)
    public ResponseEntity<SelectFloorInElevatorForUserResponse> userSelectsFloorInEleveator(@PathVariable("userIdentifier") final UUID userIdentifier,
                                                                                            @PathVariable("elevatorIdentifier") final UUID elevatorIdentifier,
                                                                                            @PathVariable("selectedFloor") final int selectedFloor) {
        SelectFloorInElevatorForUserResponse response = SelectFloorInElevatorForUserResponse.builder().build();
        try {
            response = userService.selectFloorInElevatorForUser(userIdentifier, elevatorIdentifier, selectedFloor);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
