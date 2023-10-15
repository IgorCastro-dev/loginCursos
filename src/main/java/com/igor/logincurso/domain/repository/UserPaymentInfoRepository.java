package com.igor.logincurso.domain.repository;

import com.igor.logincurso.domain.model.UserPaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentInfoRepository extends JpaRepository<UserPaymentInfo,Long> {
}
