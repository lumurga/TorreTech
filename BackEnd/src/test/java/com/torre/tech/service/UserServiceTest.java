package com.torre.tech.service;

import com.torre.tech.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.Assert.assertEquals;


@SpringBootTest
public class UserServiceTest {


    @Autowired
    UserService userService;

    @Test
    @Order(1)
    @DisplayName("New register -> User")
    void saveUser() {
        int totalUsers = userService.findAll().size();
        UserDTO categoryDTO = new UserDTO("Bosque", "Alojamientos en los mejores bosques", "imagen.jpg");
        categoryService.save(categoryDTO);
        int newTotal = categoryService.findAll().size();
        Assertions.assertNotNull(categoryDTO);
        assertTrue(totalCategories + 1 == newTotal, "Register saved succesfully");
    }

    @Test
    @Order(2)
    @DisplayName("Update register -> Category")
    void updateCategory() {
        String newTitle = "Prueba";
        CategoryDTO updatedCatDTO = categoryService.findCategoryByTitle("Bosque");
        updatedCatDTO.setTitle(newTitle);
        categoryService.update(updatedCatDTO);
        assertEquals(updatedCatDTO.getTitle(), newTitle);
    }

    @Test
    @Order(3)
    @DisplayName("Delete register -> Category")
    void deleteCategory() {
        CategoryDTO deletedCat = categoryService.findCategoryByTitle("Prueba");
        categoryService.delete(deletedCat.getId());
        CategoryDTO resp = categoryService.findCategoryByTitle("Prueba");
        Assertions.assertNull(resp);
    }

}
