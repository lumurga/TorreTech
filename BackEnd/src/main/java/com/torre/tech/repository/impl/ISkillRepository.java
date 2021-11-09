package com.torre.tech.repository.impl;

import com.torre.tech.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface ISkillRepository extends JpaRepository<Skill, Long> {
}
