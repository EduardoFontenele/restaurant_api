package com.restaurant.services;

import com.restaurant.dtos.menuSection.MenuPostInputDTO;
import com.restaurant.dtos.menuSection.MenuPostOutputDTO;
import com.restaurant.dtos.menuSection.MenuSectionGetDTO;

import java.util.List;

public interface MenuSectionService {

    List<MenuSectionGetDTO> listAll();

    MenuPostOutputDTO save(MenuPostInputDTO dto);

    void deleteById(Long id);
}
