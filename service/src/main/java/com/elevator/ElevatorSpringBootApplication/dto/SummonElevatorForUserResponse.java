package com.elevator.ElevatorSpringBootApplication.dto;

import com.elevator.ElevatorSpringBootApplication.model.Elevator;
import com.elevator.ElevatorSpringBootApplication.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonDeserialize(builder = SummonElevatorForUserResponse.SummonElevatorForUserResponseBuilder.class)
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SummonElevatorForUserResponse {
    private User user;
    private Elevator elevator;
    private String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class SummonElevatorForUserResponseBuilder {
    }
}
