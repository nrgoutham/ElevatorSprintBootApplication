package com.elevator.ElevatorSpringBootApplication.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "elevator")
@TypeDefs({
        @TypeDef(name = "status", typeClass = PostgreSQLEnumType.class),
        @TypeDef(name = "list-array", typeClass = ListArrayType.class)
})
public class Elevator {

    @Id
    @Column(name = "identifier", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID identifier;

    @Column(name = "name")
    private String name;

    @Column(name = "current_Floor")
    private Integer currentFloor;

    @Type(type = "list-array")
    @Column(name = "total_Floors", columnDefinition = "integer[]")
    private List<Integer> totalFloors;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @Type(type = "status")
    private Status state;
}
