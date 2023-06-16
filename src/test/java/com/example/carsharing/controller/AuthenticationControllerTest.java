package com.example.carsharing.controller;

import com.carsharing.controller.AuthenticationController;
import com.carsharing.dto.request.UserRequestDto;
import com.carsharing.dto.response.TokenResponseDto;
import com.carsharing.dto.response.UserResponseDto;
import com.carsharing.model.User;
import com.carsharing.security.jwt.JwtTokenProvider;
import com.carsharing.service.AuthenticationService;
import com.carsharing.service.mapper.DtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserRequestDto userRequestDto;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private DtoMapper<User, UserRequestDto, UserResponseDto> dtoMapper;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
        userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("testEmail");
        userRequestDto.setPassword("testPassword");
        userRequestDto.setFirstName("firstName");
        userRequestDto.setSecondName("secondName");
    }

    @Test
    void registerNewUser_ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
