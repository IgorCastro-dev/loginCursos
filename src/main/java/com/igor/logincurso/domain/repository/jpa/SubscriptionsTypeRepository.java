package com.igor.logincurso.domain.repository.jpa;

import com.igor.logincurso.domain.model.jpa.SubscriptionsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsTypeRepository extends JpaRepository<SubscriptionsType,Long> {

    SubscriptionsType findByProductKey(String productKey);
}
