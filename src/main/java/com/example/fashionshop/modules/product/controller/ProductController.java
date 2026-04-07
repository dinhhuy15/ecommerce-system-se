package com.example.fashionshop.modules.product.controller;

import com.example.fashionshop.common.response.ApiResponse;
import com.example.fashionshop.common.response.PaginationResponse;
import com.example.fashionshop.modules.product.dto.ProductRequest;
import com.example.fashionshop.modules.product.dto.ProductResponse;
import com.example.fashionshop.modules.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<PaginationResponse<ProductResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer categoryId) {
        return ApiResponse.success("Products fetched successfully",
                productService.getProducts(page, size, keyword, categoryId));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getDetail(@PathVariable Integer id) {
        return ApiResponse.success("Product detail fetched successfully", productService.getDetail(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        return ApiResponse.success("Product created successfully", productService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ApiResponse<ProductResponse> update(@PathVariable Integer id, @Valid @RequestBody ProductRequest request) {
        return ApiResponse.success("Product updated successfully", productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ApiResponse.success("Product deleted successfully", null);
    }

    @GetMapping("/search")
    public ApiResponse<java.util.List<ProductResponse>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String type) {
        return ApiResponse.success("Search results fetched successfully", productService.searchProducts(keyword, type));
    }
}
