package com.torre.tech.repository.impl;

import com.torre.tech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long>{


    Optional<User> findUserByName(String name);

}
