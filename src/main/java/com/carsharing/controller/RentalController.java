package com.carsharing.controller;

import com.carsharing.dto.request.RentalRequestDto;
import com.carsharing.dto.response.RentalResponseDto;
import com.carsharing.model.Rental;
import com.carsharing.service.RentalService;
import com.carsharing.service.mapper.DtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;
    private final DtoMapper<Rental, RentalRequestDto, RentalResponseDto> dtoMapper;

    @PostMapping
    @Operation(summary = "Create a new rental")
    public RentalResponseDto add(
            @Parameter(description = "Rental to add to service", required = true,
            content = @Content(schema = @Schema(implementation =
                    RentalRequestDto.class)))
            @RequestBody @Valid RentalRequestDto requestDto) {
        Rental rental =
                rentalService.createRentalWithNotificationAndDecrementingInventory(
                        dtoMapper.toModel(requestDto));
        return dtoMapper.toDto(rental);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get rental by Id")
    public RentalResponseDto findById(@Parameter(description = "id of created rental")
                                          @PathVariable Long id) {
        return dtoMapper.toDto(rentalService.get(id));
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Find rentals by User Id")
    public List<RentalResponseDto> findByUserId(
            @Parameter(description = "id of user to find all rentals")
            @PathVariable Long id,
            @Parameter(description = "Status of rental", required = true)
            @RequestParam boolean isActive) {
        return rentalService.getByUserId(id, isActive)
                .stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/return")
    @Operation(summary = "Set the rental return date")
    public RentalResponseDto setActualReturnDate(
            @Parameter(description = "id of rental")
            @PathVariable Long id,
            @Parameter(description = "Write return date", required = true)
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate actualTime) {
        return dtoMapper.toDto(rentalService.setActualReturnDate(id, actualTime));
    }
}
