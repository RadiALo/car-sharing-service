package com.example.carsharing.controller;

import com.carsharing.controller.CarController;
import com.carsharing.dto.request.CarRequestDto;
import com.carsharing.dto.response.CarResponseDto;
import com.carsharing.model.Car;
import com.carsharing.service.CarService;
import com.carsharing.service.mapper.DtoMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private CarRequestDto carRequestDto;
    private Car testCar;
    private  CarResponseDto carResponseDto;

    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carService;

    @Mock
    private DtoMapper<Car, CarRequestDto, CarResponseDto> dtoMapper;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
        testCar = new Car();
        testCar.setInventory(5);
        testCar.setBrand("TestBrand");
    }

    @Test
    void createNewCar_ok() throws Exception {
        carRequestDto = new CarRequestDto();
        carRequestDto.setBrand("testBrand");
        carRequestDto.setType(Car.Type.SEDAN);
        carRequestDto.setModel("testModel");
        carResponseDto = new CarResponseDto();
        carResponseDto.setBrand("testBrand");
        carResponseDto.setType(Car.Type.SEDAN);
        carResponseDto.setModel("testModel");

        Mockito.when(dtoMapper.toDto(testCar)).thenReturn(carResponseDto);
        Mockito.when(carService.save(testCar)).thenReturn(testCar);
        Mockito.when(dtoMapper.toModel(carRequestDto)).thenReturn(testCar);

        CarResponseDto response = carController.add(carRequestDto);
        Assertions.assertEquals(carResponseDto, response);

        mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void findAllCars_ok() throws Exception {
        carResponseDto = new CarResponseDto();
        carResponseDto.setId(testCar.getId());
        carResponseDto.setBrand(testCar.getBrand());
        carResponseDto.setType(testCar.getType());
        carResponseDto.setModel(testCar.getModel());

        Mockito.when(carService.findAllCars()).thenReturn(Collections.singletonList(testCar));
        Mockito.when(dtoMapper.toDto(testCar)).thenReturn(carResponseDto);
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<CarResponseDto> responseDtoList = objectMapper.readValue(response, new TypeReference<List<CarResponseDto>>() {});
        Assertions.assertEquals(1, responseDtoList.size());
        Assertions.assertEquals(carResponseDto, responseDtoList.get(0));
    }

    @Test
    void getCarById_ok() throws Exception {
        testCar = new Car();
        testCar.setId(1L);
        testCar.setBrand("testBrand");
        testCar.setType(Car.Type.SEDAN);
        testCar.setModel("testModel");

        carResponseDto = new CarResponseDto();
        carResponseDto.setId(testCar.getId());
        carResponseDto.setBrand(testCar.getBrand());
        carResponseDto.setType(testCar.getType());
        carResponseDto.setModel(testCar.getModel());

        Mockito.when(carService.findById(testCar.getId())).thenReturn(testCar);
        Mockito.when(dtoMapper.toDto(testCar)).thenReturn(carResponseDto);
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        CarResponseDto actualResponseDto = objectMapper.readValue(response, CarResponseDto.class);
        Assertions.assertEquals(carResponseDto, actualResponseDto);
    }

    @Test
    void updateCar_ok() throws Exception {
        carRequestDto = new CarRequestDto();
        carRequestDto.setBrand("testBrand");
        carRequestDto.setType(Car.Type.SEDAN);
        carRequestDto.setModel("testModel");

        testCar = new Car();
        testCar.setId(1L);
        testCar.setBrand("testBrand");
        testCar.setType(Car.Type.SEDAN);
        testCar.setModel("testModel");

        carResponseDto = new CarResponseDto();
        carResponseDto.setBrand("testBrand");
        carResponseDto.setType(Car.Type.SEDAN);
        carResponseDto.setModel("testModel");

        Mockito.when(dtoMapper.toModel(carRequestDto)).thenReturn(testCar);
        Mockito.when(carService.save(testCar)).thenReturn(testCar);
        Mockito.when(dtoMapper.toDto(testCar)).thenReturn(carResponseDto);

        CarResponseDto response = carController.update(testCar.getId(), carRequestDto);

        Assertions.assertEquals(carResponseDto, response);

        Mockito.when(dtoMapper.toModel(carRequestDto)).thenReturn(testCar);
        mockMvc.perform(MockMvcRequestBuilders.put("/cars/1")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteCar_ok() throws Exception {
        testCar = new Car();
        testCar.setId(1L);
        testCar.setBrand("testBrand");
        testCar.setType(Car.Type.SEDAN);
        testCar.setModel("testModel");

        mockMvc.perform(MockMvcRequestBuilders.delete("/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(carService, Mockito.times(1)).deleteById(testCar.getId());
    }
}
