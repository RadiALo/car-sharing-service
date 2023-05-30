package com.carsharing.service.mapper;

public interface ResponseMapper<D, M> {
    D fromModel(M model);
}
