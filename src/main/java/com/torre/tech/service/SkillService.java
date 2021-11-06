package com.torre.tech.service;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.torre.tech.dto.SkillDTO;
import com.torre.tech.model.Skill;
import com.torre.tech.repository.impl.ISkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service

public class SkillService implements IEntityService<SkillDTO> {

    /* Attributes */
    @Autowired
    private ISkillRepository skillRepository;
    Logger logger = Logger.getLogger(String.valueOf(SkillService.class));
    @Autowired
    private ObjectMapper mapper;



    /* Constructor */
    @Autowired
    public SkillService(ISkillRepository skillRepository, ObjectMapper mapper) {
        this.skillRepository = skillRepository;
        this.mapper = mapper;
    }

    /* Methods */
    @Override
    public SkillDTO save(SkillDTO skillDTO) {
        skillRepository.save(mapper.convertValue(skillDTO, Skill.class));
        logger.info("New skill saved successfully");
        return skillDTO;
    }

    @Override
    public Optional<SkillDTO> findById(Long id) {
        logger.info("Search by id in the Skills entity");
        SkillDTO skillDto = null;
        Optional<Skill> skill = skillRepository.findById(id);
        if(skill.isPresent()) {
            skillDto = mapper.convertValue(skill, SkillDTO.class);
        }
        return Optional.ofNullable(skillDto);
    }

    @Override
    public List<SkillDTO> findAll() {
        List<Skill> skills = skillRepository.findAll();
        List<SkillDTO> skillsDTO = new ArrayList<>();
        for(Skill s : skills) {
            skillsDTO.add(mapper.convertValue(s, SkillDTO.class));
        }
        logger.info("List of all Skills");
        return skillsDTO;
    }

    @Override
    public SkillDTO update(SkillDTO skillDtoNew) {
        skillRepository.save(mapper.convertValue(skillDtoNew, Skill.class));
        logger.info("Skill register updated correctly!");
        return skillDtoNew;
    }

    @Override
    public void delete(Long id) {
        if(skillRepository.findById(id).isPresent()){
            skillRepository.deleteById(id);
            logger.info("Skill deleted correctly!");
            System.out.println("Skill deleted correctly!");
        } else {
            logger.info("Skill not found!");
            System.out.println("Skill not found!");
        }
    }



}
