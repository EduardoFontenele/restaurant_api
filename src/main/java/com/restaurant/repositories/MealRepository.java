package com.restaurant.repositories;

import com.restaurant.entities.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MealRepository extends JpaRepository<Meal, Long> {

    //@Query(value = "SELECT m FROM meals me WHERE meal.price <= ?1")
    @Query("SELECT m FROM Meal m WHERE m.price <= :maxPrice")
    Page<Meal> findAllMealsWithFollowingMaxPrice(@Param("maxPrice") Double maximumPrice, Pageable pageable);

    Meal findByName(String name);
}
