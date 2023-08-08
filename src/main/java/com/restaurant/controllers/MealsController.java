package com.restaurant.controllers;

import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.meal.MealPatchDTO;
import com.restaurant.dtos.meal.MealPostInputDTO;
import com.restaurant.dtos.meal.MealPostOutputDTO;
import com.restaurant.exceptions.BusinessException;
import com.restaurant.exceptions.ErrorsTable;
import com.restaurant.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meals")
public class MealsController {

    private final MealService mealService;

    @GetMapping
    public ResponseEntity<Page<MealGetDTO>> listAll(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "maximumPrice", required = false) Double maximumPrice
    ) {
        return ResponseEntity.ok(mealService.listAll(pageNumber, pageSize, maximumPrice));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealGetDTO> findMealById(@PathVariable Long id) {
        if(id == null || id <= 0) throw new BusinessException(ErrorsTable.PATH_VARIABLE_ERROR);
        return ResponseEntity.ok(mealService.findMealById(id));
    }

    @PostMapping
    public ResponseEntity<MealPostOutputDTO> saveNewMeal(@RequestBody @Validated MealPostInputDTO dto) {
        return new ResponseEntity<>(mealService.saveNewMeal(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MealPatchDTO> updateMealById(@Validated @RequestBody MealPatchDTO dto, @PathVariable("id") Long id) {
        if(id == null || id <= 0) throw new BusinessException(ErrorsTable.PATH_VARIABLE_ERROR);
        return new ResponseEntity<>(mealService.updateMealById(dto, id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealById(@PathVariable Long id) {
        if(id == null || id <= 0) throw new BusinessException(ErrorsTable.PATH_VARIABLE_ERROR);
        mealService.deleteMealById(id);
        return ResponseEntity.ok().build();
    }

}
