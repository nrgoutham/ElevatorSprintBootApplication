package com.elevator.ElevatorSpringBootApplication.controller;

import com.elevator.ElevatorSpringBootApplication.model.Elevator;
import com.elevator.ElevatorSpringBootApplication.service.iface.ElevatorService;
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

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/api/v1")
public class ElevatorController {

    private final ElevatorService elevatorService;

    @Autowired
    public ElevatorController(final ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @RequestMapping(value = "elevator", method = POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Elevator> createElevator(@RequestBody final Elevator elevator) {
        final Elevator response = elevatorService.createElevator(elevator);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "elevator/{identifier}", method = PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Elevator> updateElevator(@PathVariable("identifier") final UUID identifier, @RequestBody final Elevator elevator) {
        final Elevator response = elevatorService.updateElevator(identifier, elevator);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "elevator/{identifier}", method = GET)
    public ResponseEntity<Elevator> findElevatorById(@PathVariable("identifier") final UUID identifier) {
        final Elevator response = elevatorService.getElevatorById(identifier);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "elevators", method = GET)
    public ResponseEntity<List<Elevator>> getAllElevators() {
        final List<Elevator> response = elevatorService.getAllElevators();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "elevator/{identifier}", method = DELETE)
    public ResponseEntity<Object> removeElevator(@PathVariable("identifier") final UUID identifier) {
        elevatorService.removeElevator(identifier);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
