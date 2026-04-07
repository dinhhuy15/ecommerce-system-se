package com.example.fashionshop.modules.wishlist.service;

import com.example.fashionshop.modules.wishlist.dto.WishlistResponse;

import java.util.List;

public interface WishlistService {
    List<WishlistResponse> getMyWishlist();

    void addToWishlist(Integer productId);

    void removeFromWishlist(Integer productId);
}
