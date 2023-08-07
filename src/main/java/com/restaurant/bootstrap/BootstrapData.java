package com.restaurant.bootstrap;

import com.restaurant.entities.Meal;
import com.restaurant.entities.MenuSection;
import com.restaurant.repositories.MealRepository;
import com.restaurant.repositories.MenuSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final MenuSectionRepository menuSectionRepository;
    private final MealRepository mealRepository;

    private final MenuSection appetizers = MenuSection.builder().name("Appetizers").build();
    private final MenuSection mainCourses = MenuSection.builder().name("Main Course").build();
    private final MenuSection sideDishes = MenuSection.builder().name("Side Dishes").build();
    private final MenuSection desserts = MenuSection.builder().name("Desserts").build();
    private final MenuSection drinks = MenuSection.builder().name("Drinks").build();

    private final Meal salad = Meal.builder()
            .name("Bruschetta")
            .price(new BigDecimal("20.99"))
            .menuSection(appetizers)
            .build();

    private final Meal soup = Meal.builder()
            .name("Soup")
            .price(new BigDecimal("15.00"))
            .menuSection(appetizers)
            .build();

    private final Meal steak = Meal.builder()
            .name("Steak")
            .price(new BigDecimal("32.00"))
            .menuSection(mainCourses)
            .build();

    private final Meal chicken = Meal.builder()
            .name("Chicken")
            .price(new BigDecimal("32.00"))
            .menuSection(mainCourses)
            .build();

    private final Meal rice = Meal.builder()
            .name("Rice")
            .price(new BigDecimal("18.00"))
            .menuSection(sideDishes)
            .build();

    private final Meal potatoes = Meal.builder()
            .name("Potatoes")
            .price(new BigDecimal("18.00"))
            .menuSection(sideDishes)
            .build();

    private final Meal cake = Meal.builder()
            .name("Cake")
            .price(new BigDecimal("23.00"))
            .menuSection(desserts)
            .build();

    private final Meal pie = Meal.builder()
            .name("Pie")
            .price(new BigDecimal("23.00"))
            .menuSection(desserts)
            .build();
    private final Meal water = Meal.builder()
            .name("Water")
            .price(new BigDecimal("3.00"))
            .menuSection(drinks)
            .build();

    private final Meal soda = Meal.builder()
            .name("Soda")
            .price(new BigDecimal("5.00"))
            .menuSection(drinks)
            .build();

    @Override
    public void run(String... args) throws Exception {
        loadMenu();
        loadMeals();
    }

    private void loadMenu() {
        menuSectionRepository.saveAll(Arrays.asList(appetizers, mainCourses, sideDishes, desserts, drinks));
    }

    private void loadMeals() {
        mealRepository.saveAll(Arrays.asList(salad, soup, steak, chicken, rice, potatoes, cake, pie, soda, water));
    }
}
