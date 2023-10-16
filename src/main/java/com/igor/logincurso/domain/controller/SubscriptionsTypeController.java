package com.igor.logincurso.domain.controller;

import com.igor.logincurso.domain.model.SubscriptionsType;
import com.igor.logincurso.domain.service.SubscriptionsTypeService;
import com.igor.logincurso.dto.SubscriptionsTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<SubscriptionsType> save(@RequestBody @Valid SubscriptionsTypeDto subscriptionsTypeDto){
        return ResponseEntity.ok(subscriptionsTypeService.save(subscriptionsTypeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionsType> update(@PathVariable("id") Long id,@RequestBody @Valid SubscriptionsTypeDto subscriptionsTypeDto){
        return ResponseEntity.ok(subscriptionsTypeService.update(subscriptionsTypeDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        subscriptionsTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
