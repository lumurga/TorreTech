package com.torre.tech.controller;


import com.torre.tech.dto.UserDTO;
import com.torre.tech.exception.BadRequestException;
import com.torre.tech.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    Logger logger = Logger.getLogger(String.valueOf(UserController.class));

    /* Constructor */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /* Methods */
    /* GET */

    @ApiOperation(value = "Search by id in the Users entity"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @GetMapping("id/{id}")
    public UserDTO findById(@PathVariable Long id) {
        logger.info("Search by id in the Users entity");
        return userService.findById(id).orElse(null);
    }


    @ApiOperation(value = "List of all users"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @GetMapping()
    public List<UserDTO> findAll() {
        logger.info("List of all Users");
        return userService.findAll();
    }

    @ApiOperation(value = "Search by name in the Users entity"
            ,notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = UserDTO.class ),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error") })
    @GetMapping("/{name}")
    public UserDTO findUserByName(@PathVariable String name) {
        logger.info("Search by name in the Users entity");
        return userService.findUserByName(name);
    }

    /* POST */

    @ApiOperation(value = "Insertion of a new record in the Users entity"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @PostMapping("/register")
    public ResponseEntity save(@RequestBody UserDTO userDTO) {
        ResponseEntity resp;
        if (userService.findById(userDTO.getId()) != null) {
            resp = new ResponseEntity("The user entered is already registered!", HttpStatus.CONFLICT);
            logger.info("The user is already registered!");
        } else {
            resp = new ResponseEntity(userService.save(userDTO), HttpStatus.OK);
            logger.info("New user saved successfully");
        }
        return resp;
    }

    /* PUT */
    @ApiOperation(value = "Update by id of a record in the Users entity"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserDTO update) throws BadRequestException {
        ResponseEntity resp = null;

        if (userService.findById(update.getId()).isPresent()) {
            resp = new ResponseEntity(userService.update(update), HttpStatus.OK);
            logger.info("User updated correctly");
            resp = new ResponseEntity("User not found!", HttpStatus.NOT_FOUND);
            logger.info("User not found!");
        }
        return resp;
    }


    /* DELETE */
    @ApiOperation(value = "Deletion by id of a record in the Users entity"
            ,notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = UserDTO.class ),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error") })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        ResponseEntity resp;

        if(userService.findById(id).isPresent()) {
            userService.delete(id);
            resp = new ResponseEntity(HttpStatus.NO_CONTENT);
            logger.info("User deleted correctly");
        } else {
            resp = new ResponseEntity("User not found!", HttpStatus.NOT_FOUND);
            logger.info("User not found!");
        }
        return resp;
    }
}