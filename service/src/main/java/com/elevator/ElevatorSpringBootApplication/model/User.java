package com.elevator.ElevatorSpringBootApplication.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "users")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class User {

    @Id
    @Column(name = "identifier", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID identifier;

    @Column(name = "name")
    private String name;

    @Type(type = "list-array")
    @Column(name = "building_Ids", columnDefinition = "uuid[]")
    private List<UUID> buildingIds;
}
