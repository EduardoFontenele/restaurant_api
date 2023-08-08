package com.restaurant.services;

import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.meal.MealPatchDTO;
import com.restaurant.dtos.meal.MealPostInputDTO;
import com.restaurant.dtos.meal.MealPostOutputDTO;
import org.springframework.data.domain.Page;

public interface MealService {
    Page<MealGetDTO> listAll(Integer pageNumber, Integer pageSize, Double maximumPrice);

    MealPostOutputDTO saveNewMeal(MealPostInputDTO dto);

    MealGetDTO findMealById(Long id);

    MealPatchDTO updateMealById(MealPatchDTO dto, Long id);

    void deleteMealById(Long id);
}
