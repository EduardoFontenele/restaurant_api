package com.restaurant.entities;

import com.restaurant.utils.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "costumer_name")
    private String costumerName;

    @Column(nullable = false, name = "total_price", columnDefinition = "decimal(4,2)")
    private BigDecimal totalPrice;

    @CreationTimestamp
    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    public Order(String costumerName, BigDecimal totalPrice) {
        this.costumerName = costumerName;
        this.totalPrice = totalPrice;
    }
}
