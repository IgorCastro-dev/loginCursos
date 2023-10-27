package com.igor.logincurso.domain.service;

import com.igor.logincurso.domain.model.SubscriptionsType;
import com.igor.logincurso.dto.SubscriptionsTypeDto;

import java.util.List;

public interface SubscriptionsTypeService {

    SubscriptionsType findByProductKey(String productKey);

    List<SubscriptionsType> findAll();

    SubscriptionsType findById(Long id);

    SubscriptionsType save(SubscriptionsTypeDto subscriptionsTypeDto);

    SubscriptionsType update(SubscriptionsTypeDto subscriptionsTypeDto,Long id);

    void delete(Long id);
}
