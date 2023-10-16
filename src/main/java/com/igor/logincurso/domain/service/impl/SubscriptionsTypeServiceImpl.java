package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.SubscriptionsType;
import com.igor.logincurso.domain.repository.SubscriptionsTypeRepository;
import com.igor.logincurso.domain.service.SubscriptionsTypeService;
import com.igor.logincurso.dto.SubscriptionsTypeDto;
import com.igor.logincurso.exception.BadRequestException;
import com.igor.logincurso.exception.NotFoundException;
import com.igor.logincurso.modelmapper.SubscriptionsTypeAssembler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubscriptionsTypeServiceImpl implements SubscriptionsTypeService {

    @Autowired
    private SubscriptionsTypeRepository subscriptionsTypeRepository;

    @Autowired
    private SubscriptionsTypeAssembler subscriptionsTypeAssembler;

    @Autowired
    private ModelMapper modelMapper;

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

    @Transactional
    @Override
    public SubscriptionsType update(SubscriptionsTypeDto subscriptionsTypeDto, Long id) {
        SubscriptionsType subscriptionsType = this.findById(id);
        modelMapper.map(subscriptionsTypeDto,subscriptionsType);
        SubscriptionsType subscriptionsTypeAtualizado = subscriptionsTypeRepository.save(subscriptionsType);
        return subscriptionsTypeAtualizado;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        SubscriptionsType subscriptionsType = findById(id);
        subscriptionsTypeRepository.delete(subscriptionsType);
    }
}
