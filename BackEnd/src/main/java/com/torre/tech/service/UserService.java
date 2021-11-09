package com.torre.tech.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.torre.tech.dto.UserDTO;
import com.torre.tech.model.User;
import com.torre.tech.repository.impl.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class UserService implements IEntityService<UserDTO> {

    /* Attributes */
    private IUserRepository userRepository;
    Logger logger = Logger.getLogger(String.valueOf(UserService.class));
    private ObjectMapper mapper;


    /* Constructor */
    @Autowired
    public UserService(IUserRepository userRepository, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    /* Methods */
    @Override
    public UserDTO save(UserDTO userDTO) {
        userRepository.save(mapper.convertValue(userDTO, User.class));
        logger.info("New user saved successfully");
        return userDTO;
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        logger.info("Search by id in the Users entity");
        UserDTO userDto = null;
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userDto = mapper.convertValue(user, UserDTO.class);
        }
        return Optional.ofNullable(userDto);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User u : users) {
            usersDTO.add(mapper.convertValue(u, UserDTO.class));
        }
        logger.info("List of all Users");
        return usersDTO;
    }

    @Override
    public UserDTO update(UserDTO userDtoNew) {
        userRepository.save(mapper.convertValue(userDtoNew, User.class));
        logger.info("User register updated correctly!");
        return userDtoNew;
    }

    @Override
    public void delete(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            logger.info("User deleted correctly!");
            System.out.println("User deleted correctly!");
        } else {
            logger.info("User not found!");
            System.out.println("User not found!");
        }
    }


    public UserDTO findUserByName(String name){
        logger.info("Search by name in the Users entity");

        UserDTO userDTO = null;
        User user = userRepository.findUserByName(name);
        if(user != null) {
            userDTO = mapper.convertValue(user, UserDTO.class);
        }
        return userDTO;
    }


}
