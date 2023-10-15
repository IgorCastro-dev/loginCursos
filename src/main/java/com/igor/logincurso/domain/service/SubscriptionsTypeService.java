package com.igor.logincurso.domain.service;

import com.igor.logincurso.domain.model.SubscriptionsType;
import com.igor.logincurso.dto.SubscriptionsTypeDto;

import java.util.List;

public interface SubscriptionsTypeService {

    List<SubscriptionsType> findAll();

    SubscriptionsType findById(Long id);

    SubscriptionsType save(SubscriptionsTypeDto subscriptionsTypeDto);

    SubscriptionsType update(SubscriptionsType subscriptionsType,Long id);

    void delete(Long id);
}
