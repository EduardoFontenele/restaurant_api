package com.restaurant.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostumerOrderGetDTO {

    private Long id;
    private String costumerName;
    private BigDecimal totalPrice;
    private String orderDate;
    private String orderTime;
}
