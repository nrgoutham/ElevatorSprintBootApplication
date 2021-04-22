package com.elevator.ElevatorSpringBootApplication.dto;

import com.elevator.ElevatorSpringBootApplication.model.Building;
import com.elevator.ElevatorSpringBootApplication.model.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonDeserialize(builder = UserWithBuildingsResponse.UserWithBuildingsResponseBuilder.class)
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserWithBuildingsResponse {
    private User user;
    private List<Building> buildings;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class UserWithBuildingsResponseBuilder {
    }
}
