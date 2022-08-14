package com.wkk.mapstruct;

import org.junit.Test;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/11
 */

public class MapStructTest {

    @Test
    public void shouldMapCarToDTO() {
        Car bmw = new Car("BMW", 5, CarType.SUV);
        CarDTO carDTO = CarMapper.INSTANCE.carToCarDTO(bmw);
        System.out.println(carDTO);
    }
}
