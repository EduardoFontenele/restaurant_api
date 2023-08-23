package com.restaurant.mappers;

import com.restaurant.dtos.menuSection.MenuSectionPostInputDTO;
import com.restaurant.dtos.menuSection.MenuSectionPostOutputDTO;
import com.restaurant.dtos.menuSection.MenuSectionGetDTO;
import com.restaurant.entities.MenuSection;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MenuSectionMapper {

    private final ModelMapper modelMapper;

    public MenuSectionGetDTO menuSectionEntityToGetDto(MenuSection entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, MenuSectionGetDTO.class);
    }

    public MenuSection menuSectionPostInputDtoToEntity(MenuSectionPostInputDTO dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, MenuSection.class);
    }

    public MenuSectionPostOutputDTO menuPostEntityToOutputDto(MenuSection entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, MenuSectionPostOutputDTO.class);
    }
}
