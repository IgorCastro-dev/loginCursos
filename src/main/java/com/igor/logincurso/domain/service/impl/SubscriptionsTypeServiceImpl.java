package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.SubscriptionsType;
import com.igor.logincurso.domain.repository.SubscriptionsTypeRepository;
import com.igor.logincurso.domain.service.SubscriptionsTypeService;
import com.igor.logincurso.dto.SubscriptionsTypeDto;
import com.igor.logincurso.exception.NotFoundException;
import com.igor.logincurso.modelmapper.SubscriptionsTypeAssembler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Service
public class SubscriptionsTypeServiceImpl implements SubscriptionsTypeService {

    @Autowired
    private SubscriptionsTypeRepository subscriptionsTypeRepository;

    @Autowired
    private SubscriptionsTypeAssembler subscriptionsTypeAssembler;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubscriptionsType findByProductKey(String productKey) {
        SubscriptionsType subscriptionsType = subscriptionsTypeRepository.findByProductKey(productKey);
        if (Objects.isNull(subscriptionsType)){
            throw new NotFoundException("SubscriptionsType não encontrado");
        }
        return subscriptionsType;
    }

    @Cacheable(value = "SubscriptionsType")
    @Override
    public List<SubscriptionsType> findAll() {
        return subscriptionsTypeRepository.findAll();
    }


    @Cacheable(value = "SubscriptionsType",key = "#id")
    @Override
    public SubscriptionsType findById(Long id) {
        if (subscriptionsTypeRepository.findById(id).isEmpty()){
            throw new NotFoundException("SubscriptionsType não encontrado");
        }
        return subscriptionsTypeRepository.findById(id).get();
    }

    @CacheEvict(value = "SubscriptionsType",allEntries = true)
    @Transactional
    @Override
    public SubscriptionsType save(SubscriptionsTypeDto subscriptionsTypeDto) {
        SubscriptionsType subscriptionsType = subscriptionsTypeAssembler.dtoToEntity(subscriptionsTypeDto);
        SubscriptionsType subscriptionsTypeSalvo = subscriptionsTypeRepository.save(subscriptionsType);
        return subscriptionsTypeSalvo;
    }

    @CacheEvict(value = "SubscriptionsType",allEntries = true)
    @Transactional
    @Override
    public SubscriptionsType update(SubscriptionsTypeDto subscriptionsTypeDto, Long id) {
        SubscriptionsType subscriptionsType = this.findById(id);
        modelMapper.map(subscriptionsTypeDto,subscriptionsType);
        SubscriptionsType subscriptionsTypeAtualizado = subscriptionsTypeRepository.save(subscriptionsType);
        return subscriptionsTypeAtualizado;
    }

    @CacheEvict(value = "SubscriptionsType",allEntries = true)
    @Transactional
    @Override
    public void delete(Long id) {
        SubscriptionsType subscriptionsType = findById(id);
        subscriptionsTypeRepository.delete(subscriptionsType);
    }
}
