package com.restaurant.repositories;

import com.restaurant.entities.MenuSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuSectionRepository extends JpaRepository<MenuSection, Long> {
}
