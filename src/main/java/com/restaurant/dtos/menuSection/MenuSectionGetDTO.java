package com.restaurant.dtos.menuSection;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuSectionGetDTO {
    private Long id;
    private String name;
}
