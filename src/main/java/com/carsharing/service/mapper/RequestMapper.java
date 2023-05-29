package com.carsharing.service.mapper;

public interface RequestMapper<D, M> {
    M toModel(D dto);
}
