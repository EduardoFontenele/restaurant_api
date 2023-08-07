package com.restaurant.services.impl;

import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.meal.MealPatchDTO;
import com.restaurant.dtos.meal.MealPostInputDTO;
import com.restaurant.dtos.meal.MealPostOutputDTO;
import com.restaurant.mappers.MealsMapper;
import com.restaurant.repositories.MealRepository;
import com.restaurant.repositories.MenuSectionRepository;
import com.restaurant.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final MealsMapper mapper;
    private final MenuSectionRepository menuSectionRepository;

    @Override
    public List<MealGetDTO> listAll() {
        return mealRepository.findAll().stream().map(mapper::mealEntityToGetDto).toList();
    }

    @Override
    public MealPostOutputDTO saveNewMeal(MealPostInputDTO dto) {
        return mapper.mealEntityToPostOutputDto(mealRepository.save(mapper.mealPostInputDtoToEntity(dto)));
    }

    @Override
    public MealGetDTO findMealById(Long id) {
        return mapper.mealEntityToGetDto(mealRepository.findById(id).get());
    }

    @Override
    public MealPatchDTO updateMealById(MealPatchDTO dto, Long id) {
        MealPatchDTO updatedDto = new MealPatchDTO();
        mealRepository.findById(id).ifPresent(foundMeal -> {
            if(StringUtils.hasText(dto.getName())) {
                foundMeal.setName(dto.getName());
                updatedDto.setName(dto.getName());
            }

            if(dto.getPrice() != null) {
                foundMeal.setPrice(dto.getPrice());
                updatedDto.setPrice(dto.getPrice());
            }

            if(dto.getMenuSectionId() != null) {
                foundMeal.setMenuSection(menuSectionRepository.
                        findById(dto.getMenuSectionId()).get());
                updatedDto.setMenuSectionId(dto.getMenuSectionId());
            }

            mealRepository.save(foundMeal);
        });
        return updatedDto;
    }

    @Override
    public void deleteMealById(Long id) {
        mealRepository.findById(id).ifPresent(mealRepository::delete);
    }
}
