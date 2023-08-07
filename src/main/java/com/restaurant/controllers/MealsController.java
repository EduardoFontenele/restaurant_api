package com.restaurant.controllers;

import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.meal.MealPatchDTO;
import com.restaurant.dtos.meal.MealPostInputDTO;
import com.restaurant.dtos.meal.MealPostOutputDTO;
import com.restaurant.services.MealService;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meals")
public class MealsController {

    private final MealService mealService;

    @GetMapping
    public ResponseEntity<List<MealGetDTO>> listAll() {
        return ResponseEntity.ok(mealService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealGetDTO> findMealById(@PathVariable Long id) {
        return ResponseEntity.ok(mealService.findMealById(id));
    }

    @PostMapping
    public ResponseEntity<MealPostOutputDTO> saveNewMeal(@RequestBody @Validated MealPostInputDTO dto) {
        return new ResponseEntity<>(mealService.saveNewMeal(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MealPatchDTO> updateMealById(@Validated @RequestBody MealPatchDTO dto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(mealService.updateMealById(dto, id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealById(@PathVariable Long id) {
        mealService.deleteMealById(id);
        return ResponseEntity.ok().build();
    }
}
