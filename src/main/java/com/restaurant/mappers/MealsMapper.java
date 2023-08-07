package com.restaurant.mappers;

import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.meal.MealPostInputDTO;
import com.restaurant.dtos.meal.MealPostOutputDTO;
import com.restaurant.entities.Meal;
import com.restaurant.entities.MenuSection;
import com.restaurant.repositories.MenuSectionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MealsMapper {

    private final MenuSectionRepository menuSectionRepository;
    private final ModelMapper modelMapper;

    public Meal mealPostInputDtoToEntity(MealPostInputDTO dto) {
        if(Objects.isNull(dto)) return null;

        MenuSection menuSection = menuSectionRepository.findById(dto.getMenuSectionId()).get();

        return Meal.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .menuSection(menuSection)
                .build();
    }

    public MealPostOutputDTO mealEntityToPostOutputDto(Meal entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, MealPostOutputDTO.class);
    }

    public MealGetDTO mealEntityToGetDto(Meal entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, MealGetDTO.class);
    }
}
