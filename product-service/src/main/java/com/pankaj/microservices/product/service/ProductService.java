package com.pankaj.microservices.product.service;


import com.pankaj.microservices.product.dto.ProductRequest;
import com.pankaj.microservices.product.dto.ProductResponse;
import com.pankaj.microservices.product.exception.ResourceNotFoundException;
import com.pankaj.microservices.product.model.Product;
import com.pankaj.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .imageUrl(productRequest.imageUrl())
                .productCategory(productRequest.category())
                .totalQuantity(productRequest.quantity())
                .build();
        productRepository.save(product);
        log.info("Product Created successfully");
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getImageUrl(), product.getProductCategory(), product.getTotalQuantity());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getImageUrl(), product.getProductCategory(), product.getTotalQuantity()))
                .toList();
    }


    public ProductResponse getProductById(String id) {
        return productRepository.findById(id).map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getImageUrl(), product.getProductCategory(), product.getTotalQuantity()))
                .orElseThrow(()-> new ResourceNotFoundException("Product", "Id", id));
    }

    public ProductResponse updateProduct(ProductRequest productRequest, String id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));

        existingProduct.setPrice(productRequest.price());
        existingProduct.setProductCategory(productRequest.category());
        existingProduct.setDescription(productRequest.description());
        existingProduct.setName(productRequest.name());
        existingProduct.setTotalQuantity(productRequest.quantity());
        existingProduct.setImageUrl(productRequest.imageUrl());

        productRepository.save(existingProduct);

        return new ProductResponse(existingProduct.getId(), existingProduct.getName(), existingProduct.getPrice(), existingProduct.getDescription(), existingProduct.getImageUrl(), existingProduct.getProductCategory(), existingProduct.getTotalQuantity());
    }

    public void deleteProduct(String id) {
        productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));
        productRepository.deleteById(id);

    }

}
