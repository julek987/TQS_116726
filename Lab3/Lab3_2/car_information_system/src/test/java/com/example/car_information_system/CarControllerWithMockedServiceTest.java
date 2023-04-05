package com.example.car_information_system;

import com.example.car_information_system.data.Car;
import com.example.car_information_system.service.CarManagerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CarControllerWithMockedServiceTest {
    @Autowired
    private MockMvc mvcClient;

    @MockBean
    private CarManagerService carManagerService;

//    @Test
//    public void testPostCar() throws Exception {
//        Car tesla3 = new Car("Tesla", "Model 3");
//        tesla3.setCarId(1L);
//
//        when(carManagerService.save(Mockito.any())).thenReturn(tesla3);
//
//        mvcClient.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(tesla3)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.maker", is("Tesla")))
//                .andExpect(jsonPath("$.model", is("Model 3")));
//
//        verify(carManagerService, times(1)).save(tesla3);
//    }
    @Test
    public void testGetCarIdValid() throws Exception {
        Car newCar = new Car("Tesla", "Model 3");
        newCar.setCarId(1L);

        when(carManagerService.getCarDetails(newCar.getCarId())).thenReturn(Optional.of(newCar));

        mvcClient.perform(get("/api/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("maker").value("Tesla"))
                .andExpect(jsonPath("model").value("Model 3"));

        verify(carManagerService, times(1)).getCarDetails(newCar.getCarId());
    }
}
