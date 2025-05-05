package com.github.wellls.dscommerce.repositories;

import com.github.wellls.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
