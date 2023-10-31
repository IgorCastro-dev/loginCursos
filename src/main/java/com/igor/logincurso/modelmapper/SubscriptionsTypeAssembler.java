package com.igor.logincurso.modelmapper;

import com.igor.logincurso.domain.model.jpa.SubscriptionsType;
import com.igor.logincurso.dto.SubscriptionsTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionsTypeAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public SubscriptionsTypeDto entityToDto(SubscriptionsType subscriptionsType){
        return modelMapper.map(subscriptionsType, SubscriptionsTypeDto.class);
    }

    public SubscriptionsType dtoToEntity(SubscriptionsTypeDto subscriptionsTypeDto){
        return modelMapper.map(subscriptionsTypeDto, SubscriptionsType.class);
    }
}
