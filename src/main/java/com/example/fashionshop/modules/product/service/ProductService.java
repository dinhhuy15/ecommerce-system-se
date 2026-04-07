package com.example.fashionshop.modules.product.service;

import com.example.fashionshop.common.response.PaginationResponse;
import com.example.fashionshop.modules.product.dto.ProductRequest;
import com.example.fashionshop.modules.product.dto.ProductResponse;

public interface ProductService {
    ProductResponse create(ProductRequest request);

    ProductResponse update(Integer productId, ProductRequest request);

    void delete(Integer productId);

    ProductResponse getDetail(Integer productId);

    PaginationResponse<ProductResponse> getProducts(int page, int size, String keyword, Integer categoryId);

    java.util.List<ProductResponse> searchProducts(String keyword, String type);
}
