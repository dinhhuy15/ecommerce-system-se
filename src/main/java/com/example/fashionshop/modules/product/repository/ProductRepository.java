package com.example.fashionshop.modules.product.repository;

import com.example.fashionshop.modules.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByIsActiveTrue(Pageable pageable);

    Page<Product> findByIsActiveTrueAndNameContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Product> findByIsActiveTrueAndCategoryId(Integer categoryId, Pageable pageable);

    Page<Product> findByIsActiveTrueAndCategoryIdAndNameContainingIgnoreCase(Integer categoryId, String keyword,
            Pageable pageable);

    List<Product> findTop8ByIsFeaturedTrueAndIsActiveTrueOrderByCreatedAtDesc();

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByCategoryNameContainingIgnoreCase(String type);

    List<Product> findByNameContainingIgnoreCaseAndCategoryNameContainingIgnoreCase(String keyword, String type);
}