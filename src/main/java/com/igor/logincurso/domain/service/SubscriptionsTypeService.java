package com.igor.logincurso.domain.service;

import com.igor.logincurso.domain.model.SubscriptionsType;

import java.util.List;

public interface SubscriptionsTypeService {

    List<SubscriptionsType> findAll();

    SubscriptionsType save(SubscriptionsType subscriptionsType);

    SubscriptionsType update(SubscriptionsType subscriptionsType,Long id);

    void delete(Long id);
}
