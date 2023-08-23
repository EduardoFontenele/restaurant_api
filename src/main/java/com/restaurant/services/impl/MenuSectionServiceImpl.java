package com.restaurant.services.impl;

import com.restaurant.dtos.menuSection.MenuSectionPostInputDTO;
import com.restaurant.dtos.menuSection.MenuSectionPostOutputDTO;
import com.restaurant.dtos.menuSection.MenuSectionGetDTO;
import com.restaurant.entities.MenuSection;
import com.restaurant.mappers.MenuSectionMapper;
import com.restaurant.repositories.MenuSectionRepository;
import com.restaurant.services.MenuSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuSectionServiceImpl implements MenuSectionService {

    private final MenuSectionRepository menuSectionRepository;
    private final MenuSectionMapper mapper;
    @Override
    public List<MenuSectionGetDTO> listAll() {
        return menuSectionRepository.findAll().stream().map(mapper::menuSectionEntityToGetDto).toList();
    }

    @Override
    public MenuSectionPostOutputDTO save(MenuSectionPostInputDTO dto) {
        MenuSection entity = mapper.menuSectionPostInputDtoToEntity(dto);
        return mapper.menuPostEntityToOutputDto(menuSectionRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        menuSectionRepository.deleteById(id);
    }
}
