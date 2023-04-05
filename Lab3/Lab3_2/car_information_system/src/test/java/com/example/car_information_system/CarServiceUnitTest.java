package com.example.car_information_system;

import com.example.car_information_system.data.Car;
import com.example.car_information_system.data.CarRepository;
import com.example.car_information_system.service.CarManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CarServiceUnitTest {

    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;
    @BeforeEach
    public void setUp() {
        Car tesla3 = new Car("Tesla", "Model 3");
        tesla3.setCarId(1L);
        Car teslas = new Car("Tesla", "Model S");
        teslas.setCarId(2L);
        Car teslax = new Car("Tesla", "Model X");
        teslax.setCarId(3L);

        List<Car> allCars = Arrays.asList(tesla3, teslas, teslax);

        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findByCarId(tesla3.getCarId())).thenReturn(Optional.of(tesla3));
        Mockito.when(carRepository.findByCarId(teslas.getCarId())).thenReturn(Optional.of(teslas));
        Mockito.when(carRepository.findByCarId(teslax.getCarId())).thenReturn(Optional.of(teslax));
        Mockito.when(carRepository.findByCarId(99L)).thenReturn(Optional.empty());
    }

    @Test
    public void testCarValidId() {
        Optional<Car> carFromDb = carManagerService.getCarDetails(1L);

        assertThat(carFromDb.isPresent()).isTrue();

        Car carFromOptional = carFromDb.get();

        assertThat(carFromOptional.getModel()).isEqualTo("Model 3");

        Mockito.verify(carRepository, times(1)).findByCarId(Mockito.anyLong());

    }
}
