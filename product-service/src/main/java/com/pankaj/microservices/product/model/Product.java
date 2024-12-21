package com.pankaj.microservices.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Product {

    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private String productCategory;
    private String totalQuantity;
    private String imageUrl;
}
