package com.igor.logincurso.domain.controller;

import com.igor.logincurso.domain.model.SubscriptionsType;
import com.igor.logincurso.domain.repository.SubscriptionsTypeRepository;
import com.igor.logincurso.domain.service.SubscriptionsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/subscriptions-type")
public class SubscriptionsTypeController{
    @Autowired
    private SubscriptionsTypeService subscriptionsTypeService;

    @GetMapping
    public ResponseEntity<List<SubscriptionsType>> findAll(){
        return ResponseEntity.ok(subscriptionsTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionsType> findById(@PathVariable("id") Long id){
        if(Objects.nonNull(subscriptionsTypeService.findById(id))){
            return ResponseEntity.ok(subscriptionsTypeService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
