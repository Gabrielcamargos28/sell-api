package com.order.api.SellApi.service;

import com.order.api.SellApi.domain.Order;
import com.order.api.SellApi.domain.OrderItem;
import com.order.api.SellApi.dto.request.OrderRequestDTO;
import com.order.api.SellApi.dto.response.OrderResponseDTO;
import com.order.api.SellApi.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderItemService orderItemService;

    @Transactional
    public OrderResponseDTO save(OrderRequestDTO dto) {
        Order order = Order.builder()
                .name(dto.nome())
                .email(dto.email())
                .adress(dto.endereco())
                .payamentMethod(dto.formaDePagamento())
                .build();

        Set<OrderItem> items = dto.itens().stream()
                .map(itemDto -> {
                    OrderItem item = orderItemService.createOrderItem(itemDto.idProduto(), itemDto.quantidade());
                    item.setOrder(order);
                    return item;
                })
                .collect(Collectors.toSet());

        order.setItems(items);
        Order savedOrder = orderRepository.save(order);
        return new OrderResponseDTO(savedOrder);
    }

    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
        return new OrderResponseDTO(order);
    }

    public Page<OrderResponseDTO> list(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderResponseDTO::new);
    }

    @Transactional
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Could not delete: Order not found: " + id);
        }
        orderRepository.deleteById(id);
    }

    @Transactional
    public OrderResponseDTO update(Long id, OrderRequestDTO dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found for update: " + id));

        order.setName(dto.nome());
        order.setEmail(dto.email());
        order.setAdress(dto.endereco());
        order.setPayamentMethod(dto.formaDePagamento());

        Order updatedOrder = orderRepository.save(order);
        return new OrderResponseDTO(updatedOrder);
    }
}
