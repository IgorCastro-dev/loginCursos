package com.igor.logincurso.domain.repository.jpa;

import com.igor.logincurso.domain.model.jpa.UserPaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentInfoRepository extends JpaRepository<UserPaymentInfo,Long> {
}
