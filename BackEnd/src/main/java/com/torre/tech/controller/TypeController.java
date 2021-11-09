package com.torre.tech.controller;


import com.torre.tech.dto.TypeDTO;
import com.torre.tech.exception.BadRequestException;
import com.torre.tech.service.TypeService;
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
@RequestMapping("/types")
public class TypeController {


    private TypeService typeService;
    Logger logger = Logger.getLogger(String.valueOf(TypeController.class));


    /* Constructor */
    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }



    /* Methods */
    /* GET */

    @ApiOperation(value = "Search by id in the Skill types entity"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = TypeDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @GetMapping("id/{id}")
    public TypeDTO findById(@PathVariable Long id) {
        logger.info("Search by id in the Skill types entity");
        return typeService.findById(id).orElse(null);
    }


    @ApiOperation(value = "List of all skill types"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = TypeDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @GetMapping()
    public List<TypeDTO> findAll() {
        logger.info("List of all Skill types");
        return typeService.findAll();
    }


    /* POST */
    @ApiOperation(value = "Insertion of a new record in the Skill type entity"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = TypeDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @PostMapping("/register")
    public ResponseEntity save(@RequestBody TypeDTO skillTypeDTO) {
        ResponseEntity resp;
        if (typeService.findById(skillTypeDTO.getId()) != null) {
            resp = new ResponseEntity("The skill type entered is already registered!", HttpStatus.CONFLICT);
            logger.info("The skill type is already registered!");
        } else {
            resp = new ResponseEntity(typeService.save(skillTypeDTO), HttpStatus.OK);
            logger.info("New skill type saved successfully");
        }
        return resp;
    }

    /* PUT */
    @ApiOperation(value = "Update by id of a record in the Skill types entity"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = TypeDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @PutMapping("/update")
    public ResponseEntity updateSkillType(@RequestBody TypeDTO update) throws BadRequestException {
        ResponseEntity resp = null;

        if (typeService.findById(update.getId()).isPresent()) {
            resp = new ResponseEntity(typeService.update(update), HttpStatus.OK);
            logger.info("Skill type updated correctly");
            resp = new ResponseEntity("Skill type not found!", HttpStatus.NOT_FOUND);
            logger.info("Skill type not found!");
        }
        return resp;
    }


    /* DELETE */
    @ApiOperation(value = "Deletion by id of a record in the Skill types entity"
            ,notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = TypeDTO.class ),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error") })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSkillType(@PathVariable Long id) {
        ResponseEntity resp;

        if(typeService.findById(id).isPresent()) {
            typeService.delete(id);
            resp = new ResponseEntity(HttpStatus.NO_CONTENT);
            logger.info("Skill type deleted correctly");
        } else {
            resp = new ResponseEntity("Skill type not found!", HttpStatus.NOT_FOUND);
            logger.info("Skill type not found!");
        }
        return resp;
    }
}


