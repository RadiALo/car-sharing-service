package com.example.carsharing.controller;

import com.carsharing.controller.RentalController;
import com.carsharing.dto.request.RentalRequestDto;
import com.carsharing.dto.response.RentalResponseDto;
import com.carsharing.model.Car;
import com.carsharing.model.Rental;
import com.carsharing.model.User;
import com.carsharing.service.RentalService;
import com.carsharing.service.mapper.DtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RentalControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Rental testRental;
    private Car testCar;
    private User testUser;
    private RentalResponseDto rentalResponseDto;

    @InjectMocks
    private RentalController rentalController;

    @Mock
    private RentalService rentalService;

    @Mock
    private DtoMapper<Rental, RentalRequestDto, RentalResponseDto> dtoMapper;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(rentalController).build();

        testCar = new Car();
        testCar.setId(1L);
        testCar.setBrand("testBrand");
        testCar.setType(Car.Type.SEDAN);
        testCar.setModel("testModel");
        testCar.setInventory(5);

        testUser = new User();
        testUser = new User();
        testUser.setId(1L);
        testUser.setFirstName("testFirstName");
        testUser.setSecondName("testSecondName");
        testUser.setPassword("testPassword");
        testUser.setEmail("testEmail");
        testUser.setRole(User.Role.CUSTOMER);

        testRental = new Rental();
        testRental.setId(1L);
        testRental.setUser(testUser);
        testRental.setCar(testCar);
        testRental.setRentalDate(LocalDate.now());
        testRental.setActive(true);
        testRental.setReturnDate(LocalDate.of(2023, 06, 13));
        testRental.setActualReturnDate(LocalDate.of(2023, 06, 13));

        rentalResponseDto = new RentalResponseDto();
        rentalResponseDto.setRentalDate(testRental.getRentalDate());
        rentalResponseDto.setReturnDate(testRental.getReturnDate());
        rentalResponseDto.setCarId(testRental.getCar().getId());
        rentalResponseDto.setUserId(testRental.getUser().getId());
    }

    @Test
    void findById_ok() throws Exception {
        Mockito.when(dtoMapper.toDto(testRental)).thenReturn(rentalResponseDto);
            Mockito.when(rentalService.get(testRental.getId())).thenReturn(testRental);
        RentalResponseDto actualResponseDto = rentalController.findById(testRental.getId());
        Assertions.assertEquals(rentalResponseDto, actualResponseDto);
    }

    @Test
    void findByUserId_ok() {
        List<Rental> rentals = Arrays.asList(new Rental(), new Rental());
        List<RentalResponseDto> responseDtoList = Arrays.asList(new RentalResponseDto(), new RentalResponseDto());
        Mockito.when(rentalService.getByUserId(testRental.getUser().getId(), testRental.isActive())).thenReturn(rentals);
        Mockito.when(dtoMapper.toDto(any(Rental.class))).thenReturn(responseDtoList.get(0), responseDtoList.get(1));
        List<RentalResponseDto> actualResponseDtoList =
                rentalController.findByUserId(testRental.getUser().getId(), testRental.isActive());
        Assertions.assertEquals(responseDtoList, actualResponseDtoList);
    }

    @Test
    void setActualReturnDate_ok() {
        Mockito.when(rentalService.setActualReturnDate(testRental.getId(),testRental.getActualReturnDate()))
                .thenReturn(testRental);
        Mockito.when(dtoMapper.toDto(testRental)).thenReturn(rentalResponseDto);
        RentalResponseDto actualResponseDto = rentalController
                .setActualReturnDate(testRental.getId(), testRental.getActualReturnDate());
        Assertions.assertEquals(rentalResponseDto, actualResponseDto);
    }
}
