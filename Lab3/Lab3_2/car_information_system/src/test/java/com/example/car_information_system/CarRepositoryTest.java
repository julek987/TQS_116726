package com.example.car_information_system;

import com.example.car_information_system.data.Car;
import com.example.car_information_system.data.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CarRepository carRepository;
    @Test
    public void testCarValidId() {
        Car tesla3 = new Car("Tesla", "Model 3");
        entityManager.persistAndFlush(tesla3);

        Car carFromDb = carRepository.findByCarId(tesla3.getCarId());
        assertThat(carFromDb).isNotNull();
        assertThat(carFromDb.getModel()).isEqualTo( tesla3.getModel());
    }
}