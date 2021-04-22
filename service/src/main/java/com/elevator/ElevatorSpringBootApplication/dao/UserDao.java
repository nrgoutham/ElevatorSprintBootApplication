package com.elevator.ElevatorSpringBootApplication.dao;

import com.elevator.ElevatorSpringBootApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {

    User findUserByIdentifier(UUID identifier);
}
