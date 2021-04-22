package com.elevator.ElevatorSpringBootApplication.dto;

import com.elevator.ElevatorSpringBootApplication.model.Elevator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@JsonDeserialize(builder = StatusOfAllElevatorInABuildingForUserResponse.StatusOfAllElevatorInABuildingForUserResponseBuilder.class)
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusOfAllElevatorInABuildingForUserResponse {

    UUID userIdentifier;
    UUID buildingIdentifier;
    String Message;
    List<Elevator> elevators;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class StatusOfAllElevatorInABuildingForUserResponseBuilder {
    }
}
