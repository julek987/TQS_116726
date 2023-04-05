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
//        Car FiatT = new Car("Fiat", "Tipo");
//        FiatT.setCarId(1L);
//
//        when(carManagerService.save(Mockito.any())).thenReturn(FiatT);
//
//        mvcClient.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(FiatT)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.maker", is("Fiat")))
//                .andExpect(jsonPath("$.model", is("Tipo")));
//
//        verify(carManagerService, times(1)).save(FiatT);
//    }
    @Test
    public void testGetCarIdValid() throws Exception {
        Car newCar = new Car("Fiat", "Tipo");
        newCar.setCarId(1L);

        when(carManagerService.getCarDetails(newCar.getCarId())).thenReturn(Optional.of(newCar));

        mvcClient.perform(get("/api/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("maker").value("Fiat"))
                .andExpect(jsonPath("model").value("Tipo"));

        verify(carManagerService, times(1)).getCarDetails(newCar.getCarId());
    }
}
