package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.SubscriptionsType;
import com.igor.logincurso.domain.repository.SubscriptionsTypeRepository;
import com.igor.logincurso.domain.service.SubscriptionsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionsTypeServiceImpl implements SubscriptionsTypeService {

    @Autowired
    private SubscriptionsTypeRepository subscriptionsTypeRepository;

    @Override
    public List<SubscriptionsType> findAll() {
        return subscriptionsTypeRepository.findAll();
    }

    @Override
    public SubscriptionsType findById(Long id) {
        if (subscriptionsTypeRepository.findById(id).isEmpty()){
            return null;
        }
        return subscriptionsTypeRepository.findById(id).get();
    }

    @Override
    public SubscriptionsType save(SubscriptionsType subscriptionsType) {
        return null;
    }

    @Override
    public SubscriptionsType update(SubscriptionsType subscriptionsType, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
