package ra.orderservice.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderResponse {

    private Long orderId;
    private BigDecimal totalPrice;
    private String status;

}
