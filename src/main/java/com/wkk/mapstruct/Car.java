package com.wkk.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String name;

    private int numberOfSeats;

    private CarType type;
}
