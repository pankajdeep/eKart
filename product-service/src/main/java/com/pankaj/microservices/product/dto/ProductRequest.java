package com.pankaj.microservices.product.dto;

import java.math.BigDecimal;

public record ProductRequest(String id, String name, String description, BigDecimal price, String imageUrl, String category, String quantity) {

}
