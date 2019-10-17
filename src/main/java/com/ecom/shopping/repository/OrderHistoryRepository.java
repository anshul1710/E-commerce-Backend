package com.ecom.shopping.repository;


import com.ecom.shopping.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Integer> {
    List<OrderHistory> findAllByUserId(int userId);
}
