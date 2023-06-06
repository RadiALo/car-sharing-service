package com.carsharing.service.mapper;

public interface DtoMapper<M, R, S> {
    S toDto(M model);

    M toModel(R requestDto);
}
