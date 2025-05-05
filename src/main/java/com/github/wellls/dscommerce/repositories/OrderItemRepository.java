package com.github.wellls.dscommerce.repositories;

import com.github.wellls.dscommerce.entities.OrderItem;
import com.github.wellls.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
