package com.ecom.shopping.service;

import com.ecom.shopping.model.Cart;
import com.ecom.shopping.model.OrderHistory;
import com.ecom.shopping.model.Users;
import com.ecom.shopping.model.products;
import com.ecom.shopping.repository.CartRepository;
import com.ecom.shopping.repository.OrderHistoryRepository;
import com.ecom.shopping.repository.ProductsRepository;
import com.ecom.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Transactional
@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    UserRepository userRepository;

  @Autowired
    OrderHistoryRepository orderHistoryRepository;


    public void addProduct(int user_id, int product_id) {

        products products = productsRepository.findByProductId(product_id);

        Users users = userRepository.findByUserId(user_id);

        if(cartRepository.findByUsersAndProduct(users, products)!=null) {

            Cart cart = (Cart) cartRepository.findByUsersAndProduct(users, products);
            cart.setQuantity(cart.getQuantity()+1);
            cartRepository.save(cart);
        } else {
            Cart cart = new Cart(1,products, users );
            cartRepository.save(cart);
        }

        /*return cartRepository.findByUsersAndProducts(users, products);*/
    }


    public void removeProduct(int userId, int productId) {
        products products = productsRepository.findByProductId(productId);
        Users users = userRepository.findByUserId(userId);
        cartRepository.deleteAllByUsersAndProduct(users, products);
        /*return "removed";*/
    }

    public List<Cart> showUserProducts(int userId) {
        Users users = userRepository.findByUserId(userId);
        return cartRepository.findByUsers(users);
    }


    public void subtractProduct(int userId, int productId) {

        products products = productsRepository.findByProductId(productId);

        Users users = userRepository.findByUserId(userId);

        if(cartRepository.findByUsersAndProduct(users, products)!=null) {

            Cart cart = (Cart) cartRepository.findByUsersAndProduct(users, products);
            if(cart.getQuantity()>=2) {
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepository.save(cart);
            } else if(cart.getQuantity()==1) {
                removeProduct(userId, productId);
            }
        }
        /*return (Cart) cartRepository.findByUsersAndProducts(users, products);*/

    }

    public List<OrderHistory> checkout(Principal principal) {
        Users users = userRepository.getByEmail(principal.getName());
        ArrayList<Cart> cartList = cartRepository.findAllByUsers(users);
        for(Cart cart : cartList) {
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setUserId(cart.getUsers().getUserId());
            orderHistory.setQuantity(cart.getQuantity());
            orderHistory.setPrice(cart.getProduct().getPrice());
            orderHistory.setName(cart.getProduct().getName());
            orderHistory.setImage(cart.getProduct().getImage());
            orderHistory.setProductId(cart.getProduct().getProductId());
            orderHistory.setDate(new Date());
            cartRepository.delete(cart);
            orderHistoryRepository.saveAndFlush(orderHistory);
        }
        return orderHistoryRepository.findAllByUserId(users.getUserId());
    }
}
