package com.github.wellls.dscommerce.services;

import com.github.wellls.dscommerce.dtos.OrderDTO;
import com.github.wellls.dscommerce.entities.Order;
import com.github.wellls.dscommerce.repositories.OrderRepository;
import com.github.wellls.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return new OrderDTO(order);
    }
}
