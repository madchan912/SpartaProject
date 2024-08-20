package com.sparta.ecommerce.service;

import com.sparta.ecommerce.dto.OrderRequestDto;
import com.sparta.ecommerce.dto.OrderResponseDto;
import com.sparta.ecommerce.entity.Order;
import com.sparta.ecommerce.entity.OrderItem;
import com.sparta.ecommerce.entity.Product;
import com.sparta.ecommerce.entity.User;
import com.sparta.ecommerce.repository.OrderItemRepository;
import com.sparta.ecommerce.repository.OrderRepository;
import com.sparta.ecommerce.repository.ProductRepository;
import com.sparta.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // 주문 목록 조회
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 주문 내역 상세 조회
    public Order getOrderDetails(Long orderNo) {
        return orderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
    }

    // 상품 주문
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        User user = userRepository.findById(orderRequestDto.getUsrId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Order order = new Order();
        order.setUser(user);
        order.setDate(LocalDateTime.now());
        order.setStatus("주문완료");

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderRequestDto.OrderItemDto itemDto : orderRequestDto.getItems()) {
            Product product = productRepository.findById(itemDto.getPrdId())
                    .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());

            product.setQuantity(product.getQuantity() - itemDto.getQuantity());  // 재고 감소
            productRepository.save(product);  // 변경된 재고 수량을 데이터베이스에 반영

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return new OrderResponseDto(order.getOrderId(), "주문이 성공적으로 완료되었습니다.");
    }

    // 주문 취소
    public void cancelOrder(Long orderNo) {
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));

        if (!order.getStatus().equals("배송준비중") && !order.getStatus().equals("주문완료")) {
            throw new IllegalStateException("주문 취소는 배송 중이 되기 이전에만 가능합니다.");
        }

        // 주문 항목의 재고 복구
        List<OrderItem> orderItems = orderItemRepository.findByOrderOrderId(orderNo);
        for (OrderItem item : orderItems) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() + item.getQuantity());
            productRepository.save(product);
        }

        // 주문 상태를 취소 완료로 변경
        order.setStatus("취소완료");
        orderRepository.save(order);
    }

    // 주문 반품
    public void returnOrder(Long orderNo) {
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));

        if (!order.getStatus().equals("배송완료")) {
            throw new IllegalStateException("반품은 배송 완료된 주문에 대해서만 가능합니다.");
        }

        // 반품 가능 기간 확인
        LocalDateTime deliveredDate = order.getDate();
        LocalDateTime returnDeadline = deliveredDate.plusDays(1);
        if (LocalDateTime.now().isAfter(returnDeadline)) {
            throw new IllegalStateException("반품 가능 기간이 지났습니다.");
        }

        // 일정 시간 이후 재고 복구 및 반품 상태 변경
        List<OrderItem> orderItems = orderItemRepository.findByOrderOrderId(orderNo);
        for (OrderItem item : orderItems) {
            Product product = item.getProduct();

            // 1일 후 재고 복구 및 반품 상태 변경 (비동기 처리 추천)
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    product.setQuantity(product.getQuantity() + item.getQuantity());
                    productRepository.save(product);
                    order.setStatus("반품완료");
                    orderRepository.save(order);
                }
            }, 24 * 60 * 60 * 1000); // 1일 후 실행
        }
    }
}
