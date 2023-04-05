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
        Car FiatT = new Car("Fiat", "Tipo");
        FiatT.setCarId(1L);
        Car FiatT2 = new Car("FIat", "Tipo 2");
        FiatT2.setCarId(2L);
        Car FiatSuper = new Car("Fiat", "Tipo Super");
        FiatSuper.setCarId(3L);

        List<Car> allCars = Arrays.asList(FiatT, FiatT2, FiatSuper);

        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findByCarId(FiatT.getCarId())).thenReturn(Optional.of(FiatT));
        Mockito.when(carRepository.findByCarId(FiatT2.getCarId())).thenReturn(Optional.of(FiatT2));
        Mockito.when(carRepository.findByCarId(FiatSuper.getCarId())).thenReturn(Optional.of(FiatSuper));
        Mockito.when(carRepository.findByCarId(99L)).thenReturn(Optional.empty());
    }

    @Test
    public void testCarValidId() {
        Optional<Car> carFromDb = carManagerService.getCarDetails(1L);

        assertThat(carFromDb.isPresent()).isTrue();

        Car carFromOptional = carFromDb.get();

        assertThat(carFromOptional.getModel()).isEqualTo("Tipo");

        Mockito.verify(carRepository, times(1)).findByCarId(Mockito.anyLong());

    }
}
