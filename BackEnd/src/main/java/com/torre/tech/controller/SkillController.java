package com.torre.tech.controller;

import com.torre.tech.dto.SkillDTO;
import com.torre.tech.exception.BadRequestException;
import com.torre.tech.service.SkillService;
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
@RequestMapping("/skills")
public class SkillController {

    private SkillService skillService;
    Logger logger = Logger.getLogger(String.valueOf(SkillController.class));


    /* Constructor */
    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }



    /* Methods */
    /* GET */

    @ApiOperation(value = "Search by id in the Skills entity"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = SkillDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @GetMapping("id/{id}")
    public SkillDTO findById(@PathVariable Long id) {
        logger.info("Search by id in the Skills entity");
        return skillService.findById(id).orElse(null);
    }


    @ApiOperation(value = "List of all skills"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = SkillDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @GetMapping()
    public List<SkillDTO> findAll() {
        logger.info("List of all Skills");
        return skillService.findAll();
    }


    /* POST */
    @ApiOperation(value = "Insertion of a new record in the Skills entity"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = SkillDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @PostMapping("/register")
    public ResponseEntity save(@RequestBody SkillDTO skillDTO) {
        ResponseEntity resp;
        if (skillService.findById(skillDTO.getId()) != null) {
            resp = new ResponseEntity("The skill entered is already registered!", HttpStatus.CONFLICT);
            logger.info("The skill is already registered!");
        } else {
            resp = new ResponseEntity(skillService.save(skillDTO), HttpStatus.OK);
            logger.info("New skill saved successfully");
        }
        return resp;
    }

    /* PUT */
    @ApiOperation(value = "Update by id of a record in the Skills entity"
            , notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = SkillDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error")})
    @PutMapping("/update")
    public ResponseEntity updateSkill(@RequestBody SkillDTO update) throws BadRequestException {
        ResponseEntity resp = null;

        if (skillService.findById(update.getId()).isPresent()) {
            resp = new ResponseEntity(skillService.update(update), HttpStatus.OK);
            logger.info("Skill updated correctly");
            resp = new ResponseEntity("Skill not found!", HttpStatus.NOT_FOUND);
            logger.info("Skill not found!");
        }
        return resp;
    }


    /* DELETE */
    @ApiOperation(value = "Deletion by id of a record in the Skills entity"
            ,notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The resource is obtained correctly", response = SkillDTO.class ),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Unexpected error") })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSkill(@PathVariable Long id) {
        ResponseEntity resp;

        if(skillService.findById(id).isPresent()) {
            skillService.delete(id);
            resp = new ResponseEntity(HttpStatus.NO_CONTENT);
            logger.info("Skill deleted correctly");
        } else {
            resp = new ResponseEntity("Skill not found!", HttpStatus.NOT_FOUND);
            logger.info("Skill not found!");
        }
        return resp;
    }
}
