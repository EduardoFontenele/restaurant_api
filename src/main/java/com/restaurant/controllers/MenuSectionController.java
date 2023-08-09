package com.restaurant.controllers;

import com.restaurant.dtos.menuSection.MenuPostInputDTO;
import com.restaurant.dtos.menuSection.MenuPostOutputDTO;
import com.restaurant.dtos.menuSection.MenuSectionGetDTO;
import com.restaurant.services.MenuSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menuSections")
public class MenuSectionController {

    private final MenuSectionService menuSectionService;

    @GetMapping
    public List<MenuSectionGetDTO> listAll() {
        return menuSectionService.listAll();
    }

    @PostMapping
    public MenuPostOutputDTO createNewMenuSection(@Validated @RequestBody MenuPostInputDTO dto) {
        return menuSectionService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        menuSectionService.deleteById(id);
    }
}
