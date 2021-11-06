package com.torre.tech.repository.impl;

import com.torre.tech.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface ITypeRepository extends JpaRepository<Type, Long> {
}
