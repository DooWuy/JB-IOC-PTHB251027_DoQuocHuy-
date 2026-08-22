package ra.orderservice.config.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ra.orderservice.dto.ProductResponse;

@FeignClient(
        name = "product-service",
        url = "${services.product-service.url}"
)
public interface ProductClient {


    @GetMapping("/api/products/{id}")
    ProductResponse getProductById(@PathVariable("id") Long id);

}
