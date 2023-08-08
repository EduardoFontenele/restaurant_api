package com.restaurant.services.impl;

import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.meal.MealPatchDTO;
import com.restaurant.dtos.meal.MealPostInputDTO;
import com.restaurant.dtos.meal.MealPostOutputDTO;
import com.restaurant.entities.Meal;
import com.restaurant.exceptions.BusinessException;
import com.restaurant.exceptions.ErrorsTable;
import com.restaurant.mappers.MealsMapper;
import com.restaurant.repositories.MealRepository;
import com.restaurant.repositories.MenuSectionRepository;
import com.restaurant.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final MealsMapper mapper;
    private final MenuSectionRepository menuSectionRepository;

    @Override
    public Page<MealGetDTO> listAll(Integer pageNumber, Integer pageSize, Double maximumPrice) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<Meal> page;

        if(maximumPrice != null) {
            page = mealRepository.findAllMealsWithFollowingMaxPrice(maximumPrice, pageRequest);
        } else {
            page = mealRepository.findAll(pageRequest);
        }

        return page.map(mapper::mealEntityToGetDto);
    }

    private PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber = 0;
        int queryPageSize = 5;

        if(pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        }

        if(pageSize != null && pageSize > 0) {
            if(pageSize >= 30) {
                queryPageSize = 30;
            } else {
                queryPageSize = pageSize;
            }
        }

        return PageRequest.of(queryPageNumber, queryPageSize);
    }

    @Override
    public MealGetDTO findMealById(Long id) {
        if(mealRepository.findById(id).isPresent()) {
            return mapper.mealEntityToGetDto(mealRepository.findById(id).get());
        } else {
            throw new BusinessException(ErrorsTable.NOT_FOUND_ERROR, String.format("MEAL with ID %d", id));
        }
    }

    @Override
    public MealPostOutputDTO saveNewMeal(MealPostInputDTO dto) {
        if(mealRepository.findByName(dto.getName()) != null)
            throw new BusinessException(ErrorsTable.RESOURCE_ALREADY_EXISTS,
                    String.format("%s", dto.getName().toUpperCase()));
        return mapper.mealEntityToPostOutputDto(mealRepository.save(mapper.mealPostInputDtoToEntity(dto)));
    }

    @Override
    public MealPatchDTO updateMealById(MealPatchDTO dto, Long id) {
        MealPatchDTO updatedDto = new MealPatchDTO();
        if(mealRepository.findById(id).isPresent()) {
            Meal foundMeal = mealRepository.findById(id).get();

            if(StringUtils.hasText(dto.getName())) {
                foundMeal.setName(dto.getName());
                updatedDto.setName(dto.getName());
            }

            if(dto.getPrice() != null) {
                foundMeal.setPrice(dto.getPrice());
                updatedDto.setPrice(dto.getPrice());
            }

            if(dto.getMenuSectionId() != null) {
                if(menuSectionRepository.findById(dto.getMenuSectionId()).isEmpty())
                    throw new BusinessException(ErrorsTable.NOT_FOUND_ERROR, String.format("MENU SECTION with ID %d",
                            dto.getMenuSectionId()));
                foundMeal.setMenuSection(menuSectionRepository.findById(dto.getMenuSectionId()).get());
                updatedDto.setMenuSectionId(dto.getMenuSectionId());
            }

            mealRepository.save(foundMeal);
        } else {
            throw new BusinessException(ErrorsTable.NOT_FOUND_ERROR, String.format("MEAL with ID %d", id));
        }
        return updatedDto;
    }

    @Override
    public void deleteMealById(Long id) {
        if(mealRepository.findById(id).isPresent()) {
            mealRepository.deleteById(id);
        } else {
            throw new BusinessException(ErrorsTable.NOT_FOUND_ERROR, String.format("MEAL with ID %d", id));
        }
    }
}
