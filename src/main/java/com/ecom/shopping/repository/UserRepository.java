package com.ecom.shopping.repository;

import com.ecom.shopping.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<Users,Integer>
{

     Users findByEmail(String email);
     Users findByUserId(Integer id);
    Users getByEmail(String name);
}
