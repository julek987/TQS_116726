package com.example.car_information_system.boundary;

import com.example.car_information_system.data.Car;
import com.example.car_information_system.service.CarManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarManagerService carManagerService;

    @GetMapping(path = "/cars")
    public List<Car> getCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping(path = "/cars/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable(value = "id") Long carId) throws MissingResourceException {
        Optional<Car> foundCar = carManagerService.getCarDetails(carId);
        return ResponseEntity.ok().body(foundCar);
    }

    @PostMapping(path = "/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car newCar) {
        return new ResponseEntity<Car>(carManagerService.save(newCar), HttpStatus.CREATED);
    }

}