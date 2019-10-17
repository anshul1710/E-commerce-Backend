package com.ecom.shopping.controller;


import com.ecom.shopping.model.Cart;
import com.ecom.shopping.model.OrderHistory;
import com.ecom.shopping.service.CartService;
import com.ecom.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @GetMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable("productId") int productId, Principal principal) {
        cartService.addProduct(userService.getUserId(principal), productId);
        return "\"Added Product To Cart\"";
    }

    @GetMapping("/removeOneFromCart/{productId}")
    public String removeOneFromCart(@PathVariable("productId") int productId, Principal principal) {
        cartService.subtractProduct(userService.getUserId(principal), productId);
        return "\"Removed One Product\"";
    }

    @GetMapping("/removeFromCart/{productId}")
    public String removeFromCart(@PathVariable("productId") int productId, Principal principal) {
        cartService.removeProduct(userService.getUserId(principal), productId);
        return "\"Product Removed\"";
    }

    @GetMapping("/showCart")
    public List<Cart> showCart(Principal principal) {
        return cartService.showUserProducts(userService.getUserId(principal));
    }
    @GetMapping("/checkout")
    public List<OrderHistory> checkOutFromCart(Principal principal) {
        return cartService.checkout(principal);

    }

}
