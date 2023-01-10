package com.erdemnayin.edabasedomains.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String name;
    private Long quantity;
    private BigDecimal price;
}
