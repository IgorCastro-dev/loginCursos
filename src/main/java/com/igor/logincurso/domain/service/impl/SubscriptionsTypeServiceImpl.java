package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.SubscriptionsType;
import com.igor.logincurso.domain.repository.SubscriptionsTypeRepository;
import com.igor.logincurso.domain.service.SubscriptionsTypeService;
import com.igor.logincurso.dto.SubscriptionsTypeDto;
import com.igor.logincurso.exception.NotFoundException;
import com.igor.logincurso.modelmapper.SubscriptionsTypeAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubscriptionsTypeServiceImpl implements SubscriptionsTypeService {

    @Autowired
    private SubscriptionsTypeRepository subscriptionsTypeRepository;

    @Autowired
    private SubscriptionsTypeAssembler subscriptionsTypeAssembler;

    @Override
    public List<SubscriptionsType> findAll() {
        return subscriptionsTypeRepository.findAll();
    }

    @Override
    public SubscriptionsType findById(Long id) {
        if (subscriptionsTypeRepository.findById(id).isEmpty()){
            throw new NotFoundException("SubscriptionsType não encontrado");
        }
        return subscriptionsTypeRepository.findById(id).get();
    }

    @Transactional
    @Override
    public SubscriptionsType save(SubscriptionsTypeDto subscriptionsTypeDto) {
        SubscriptionsType subscriptionsType = subscriptionsTypeAssembler.dtoToEntity(subscriptionsTypeDto);
        SubscriptionsType subscriptionsTypeSalvo = subscriptionsTypeRepository.save(subscriptionsType);
        return subscriptionsTypeSalvo;
    }

    @Override
    public SubscriptionsType update(SubscriptionsType subscriptionsType, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
