package ra.orderservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.orderservice.config.client.ProductClient;
import ra.orderservice.dto.CreateOrderRequest;
import ra.orderservice.dto.ProductResponse;
import ra.orderservice.dto.response.OrderResponse;
import ra.orderservice.entity.Order;
import ra.orderservice.repository.OrderRepository;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(CreateOrderRequest request) {
        ProductResponse product;

        try {
            product = productClient.getProductById(request.getProductId());
        } catch (FeignException.NotFound ex) {
            throw new IllegalArgumentException("product khong du so luong ton kho ");
        }

        if (product.getStockQuantity() < request.getQuantity()) {
            throw new IllegalArgumentException("product khong du so luong ton kho");
        }

        BigDecimal totalPrice = product.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .customerName(request.getCustomerName())
                .totalPrice(totalPrice)
                .status("confirm")
                .build();

        Order savedOrder = orderRepository.save(order);

        return OrderResponse.builder()
                .orderId(savedOrder.getId())
                .totalPrice(savedOrder.getTotalPrice())
                .status(savedOrder.getStatus())
                .build();
    }
}
