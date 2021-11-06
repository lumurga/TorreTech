package com.torre.tech.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.torre.tech.dto.TypeDTO;
import com.torre.tech.model.Type;
import com.torre.tech.repository.impl.ITypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TypeService implements IEntityService<TypeDTO> {

    /* Attributes */
    private ITypeRepository typeRepository;
    Logger logger = Logger.getLogger(String.valueOf(TypeService.class));
    private ObjectMapper mapper;



    /* Constructor */
    @Autowired
    public TypeService(ITypeRepository typeRepository, ObjectMapper mapper) {
        this.typeRepository = typeRepository;
        this.mapper = mapper;
    }

    /* Methods */
    @Override
    public TypeDTO save(TypeDTO typeDTO) {
        typeRepository.save(mapper.convertValue(typeDTO, Type.class));
        logger.info("New skill type saved successfully");
        return typeDTO;
    }

    @Override
    public Optional<TypeDTO> findById(Long id) {
        logger.info("Search by id in the Skill type entity");
        TypeDTO stDto = null;
        Optional<Type> st = typeRepository.findById(id);
        if(st.isPresent()) {
            stDto = mapper.convertValue(st, TypeDTO.class);
        }
        return Optional.ofNullable(stDto);
    }

    @Override
    public List<TypeDTO> findAll() {
        List<Type> skillTypes = typeRepository.findAll();
        List<TypeDTO> stDTO = new ArrayList<>();
        for(Type st : skillTypes) {
            stDTO.add(mapper.convertValue(st, TypeDTO.class));
        }
        logger.info("List of all Skill types");
        return stDTO;
    }

    @Override
    public TypeDTO update(TypeDTO skillTypeDTONew) {
        typeRepository.save(mapper.convertValue(skillTypeDTONew, Type.class));
        logger.info("Register updated correctly!");
        return skillTypeDTONew;
    }

    @Override
    public void delete(Long id) {
        if(typeRepository.findById(id).isPresent()){
            typeRepository.deleteById(id);
            logger.info("Deleted correctly!");
            System.out.println("Deleted  correctly!");
        } else {
            logger.info("Not found!");
            System.out.println("Not found!");
        }
    }
}
