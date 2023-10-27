package com.igor.logincurso.domain.repository;

import com.igor.logincurso.domain.model.SubscriptionsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsTypeRepository extends JpaRepository<SubscriptionsType,Long> {

    SubscriptionsType findByProductKey(String productKey);
}
