package com.example.fashionshop.modules.product.service;

import com.example.fashionshop.common.exception.ResourceNotFoundException;
import com.example.fashionshop.common.mapper.ProductMapper;
import com.example.fashionshop.common.response.PaginationResponse;
import com.example.fashionshop.common.util.SecurityUtil;
import com.example.fashionshop.modules.category.entity.Category;
import com.example.fashionshop.modules.category.repository.CategoryRepository;
import com.example.fashionshop.modules.product.dto.ProductRequest;
import com.example.fashionshop.modules.product.dto.ProductResponse;
import com.example.fashionshop.modules.product.entity.Product;
import com.example.fashionshop.modules.product.repository.ProductRepository;
import com.example.fashionshop.modules.user.entity.User;
import com.example.fashionshop.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public ProductResponse create(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        User creator = getCurrentUser();

        Product product = Product.builder()
                .category(category)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .stockQuantity(request.getStockQuantity())
                .isActive(true)
                .createdBy(creator)
                .updatedBy(creator)
                .build();
        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse update(Integer productId, ProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        product.setCategory(category);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        product.setStockQuantity(request.getStockQuantity());
        product.setUpdatedBy(getCurrentUser());

        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void delete(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setIsActive(false);
        productRepository.save(product);
    }

    @Override
    public ProductResponse getDetail(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return ProductMapper.toResponse(product);
    }

    @Override
    public PaginationResponse<ProductResponse> getProducts(int page, int size, String keyword, Integer categoryId) {
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasCategoryId = categoryId != null;
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Product> result;
        if (hasCategoryId && hasKeyword) {
            result = productRepository.findByIsActiveTrueAndCategoryIdAndNameContainingIgnoreCase(categoryId, keyword,
                    pageRequest);
        } else if (hasCategoryId) {
            result = productRepository.findByIsActiveTrueAndCategoryId(categoryId, pageRequest);
        } else if (hasKeyword) {
            result = productRepository.findByIsActiveTrueAndNameContainingIgnoreCase(keyword, pageRequest);
        } else {
            result = productRepository.findByIsActiveTrue(pageRequest);
        }

        return PaginationResponse.<ProductResponse>builder()
                .items(result.getContent().stream().map(ProductMapper::toResponse).toList())
                .page(result.getNumber())
                .size(result.getSize())
                .totalItems(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();
    }

    @Override
    public java.util.List<ProductResponse> searchProducts(String keyword, String type) {
        java.util.List<Product> products;
        if (keyword != null && !keyword.trim().isEmpty()) {
            if (type != null && !type.trim().isEmpty()) {
                products = productRepository.findByNameContainingIgnoreCaseAndCategoryNameContainingIgnoreCase(keyword,
                        type);
            } else {
                products = productRepository.findByNameContainingIgnoreCase(keyword);
            }
        } else if (type != null && !type.trim().isEmpty()) {
            products = productRepository.findByCategoryNameContainingIgnoreCase(type);
        } else {
            products = productRepository.findAll();
        }
        return products.stream().map(ProductMapper::toResponse).toList();
    }

    private User getCurrentUser() {
        String email = SecurityUtil.getCurrentUsername();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
