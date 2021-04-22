package com.elevator.ElevatorSpringBootApplication.controller;

import com.elevator.ElevatorSpringBootApplication.model.Building;
import com.elevator.ElevatorSpringBootApplication.service.iface.BuildingService;
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
public class BuildingController {

    private final BuildingService buildingService;

    @Autowired
    public BuildingController(final BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @RequestMapping(value = "building", method = POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Building> createBuilding(@RequestBody final Building building) {
        final Building response = buildingService.createBuilding(building);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "building/{identifier}", method = PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Building> updateBuilding(@PathVariable("identifier") final UUID identifier, @RequestBody final Building building) {
        final Building response = buildingService.updateBuilding(identifier, building);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "building/{identifier}", method = GET)
    public ResponseEntity<Building> getBuildingById(@PathVariable("identifier") final UUID identifier) {
        final Building response = buildingService.getBuildingById(identifier);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "buildings", method = GET)
    public ResponseEntity<List<Building>> getAllBuildings() {
        final List<Building> response = buildingService.getAllBuildings();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "building/{identifier}", method = DELETE)
    public ResponseEntity<Object> removeBuilding(@PathVariable("identifier") final UUID identifier) {
        buildingService.removeBuilding(identifier);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
