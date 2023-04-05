package com.example.car_information_system;

import com.example.car_information_system.data.Car;
import com.example.car_information_system.data.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarControllerTestIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }
    @Test
    public void testPostCar() {
        Car tesla3 = new Car("Fiat", "Tipo");
        tesla3.setCarId(1L);
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", tesla3, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("Tipo");
    }
    @Test
    public void testGetCarIdValid() {
        Car newCar = new Car("Fiat", "Tipo");
        repository.saveAndFlush(newCar);

        ResponseEntity<Car> response = restTemplate
                .exchange("/api/cars/"+newCar.getCarId(), HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).isEqualTo("Tipo");

    }
}