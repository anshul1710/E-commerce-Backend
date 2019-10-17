package com.ecom.shopping.model;

import javax.persistence.*;

@Entity
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int quantity;

    @ManyToOne
    products product;

    @ManyToOne
    Users users;

    public Cart() {}

    public Cart(int quantity, products product, Users users) {
        this.quantity = quantity;
        this.product = product;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public products getProduct() {
        return product;
    }

    public void setProduct(products product) {
        this.product = product;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
