package com.ecom.shopping.repository;

import com.ecom.shopping.model.products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<products,Integer>
{
        List<products> findAllByCategory(String category);
        List<products> findByPriceBetween(double min,double max);
        List<products> findByCategoryAndPriceBetween(String category,double min,double max);

        products findByProductId(int id);
}
