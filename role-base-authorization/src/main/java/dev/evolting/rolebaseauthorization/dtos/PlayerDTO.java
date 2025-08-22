package dev.evolting.rolebaseauthorization.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private Integer id;
    private String name;
    private Integer age;
    private String position;
    private String number;
    private String clubName;
}
