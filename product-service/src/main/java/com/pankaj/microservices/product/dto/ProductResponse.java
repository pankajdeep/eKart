package com.pankaj.microservices.product.dto;

import java.math.BigDecimal;

public record ProductResponse(String productId, String productName, BigDecimal productPrice, String productDescription, String productImageUrl, String productCategory, String totalQuantity) {
}
