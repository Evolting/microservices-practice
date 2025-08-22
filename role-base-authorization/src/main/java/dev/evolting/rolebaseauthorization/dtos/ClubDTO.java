package dev.evolting.rolebaseauthorization.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubDTO {
    private Integer id;
    private String name;
    private String description;
}
