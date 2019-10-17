package com.ecom.shopping.repository;

import com.ecom.shopping.model.Cart;
import com.ecom.shopping.model.Users;
import com.ecom.shopping.model.products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    Object findByUsersAndProduct(Users users, products products);

    List<Cart> findByUsers(Users users);

    String deleteAllByUsersAndProduct(Users users, products products);

    ArrayList<Cart> findAllByUsers(Users users);
}
