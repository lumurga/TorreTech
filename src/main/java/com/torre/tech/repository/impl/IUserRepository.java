package com.torre.tech.repository.impl;

import com.torre.tech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;





@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    public User findUserByName(String name);

}
