package ra.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    private Long productId;
    private Integer quantity;
    private String customerName;
}
