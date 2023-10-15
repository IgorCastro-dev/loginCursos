package com.igor.logincurso.domain.controller;

import com.igor.logincurso.domain.model.SubscriptionsType;
import com.igor.logincurso.domain.service.SubscriptionsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        return ResponseEntity.ok(subscriptionsTypeService.findById(id));
    }

}
