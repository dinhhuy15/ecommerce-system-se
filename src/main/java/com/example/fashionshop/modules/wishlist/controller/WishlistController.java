package com.example.fashionshop.modules.wishlist.controller;

import com.example.fashionshop.common.response.ApiResponse;
import com.example.fashionshop.modules.wishlist.dto.WishlistResponse;
import com.example.fashionshop.modules.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public ApiResponse<List<WishlistResponse>> getMyWishlist() {
        return ApiResponse.success("Wishlist fetched successfully", wishlistService.getMyWishlist());
    }

    @PostMapping("/{productId}")
    public ApiResponse<Void> addToWishlist(@PathVariable Integer productId) {
        wishlistService.addToWishlist(productId);
        return ApiResponse.success("Added to wishlist successfully", null);
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<Void> removeFromWishlist(@PathVariable Integer productId) {
        wishlistService.removeFromWishlist(productId);
        return ApiResponse.success("Removed from wishlist successfully", null);
    }
}
