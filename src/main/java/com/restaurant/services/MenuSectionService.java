package com.restaurant.services;

import com.restaurant.dtos.menuSection.MenuSectionPostInputDTO;
import com.restaurant.dtos.menuSection.MenuSectionPostOutputDTO;
import com.restaurant.dtos.menuSection.MenuSectionGetDTO;

import java.util.List;

public interface MenuSectionService {

    List<MenuSectionGetDTO> listAll();

    MenuSectionPostOutputDTO save(MenuSectionPostInputDTO dto);

    void deleteById(Long id);
}
