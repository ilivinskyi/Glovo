package com.ilivinskyi.glovo.glovo.repository;

import com.ilivinskyi.glovo.glovo.models.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
}