package ra.productservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ra.productservice.entity.Product;
import ra.productservice.repository.ProductRepository;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataSeeding  implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            productRepository.save(
                    Product.builder()
                            .id(1L)
                            .name("Bàn phím cơ DareU")
                            .price(BigDecimal.valueOf(650000))
                            .stockQuantity(15)
                            .build()
            );

            productRepository.save(
                    Product.builder()
                            .id(2L)
                            .name("Chuột Logitech G102")
                            .price(BigDecimal.valueOf(400000))
                            .stockQuantity(0)
                            .build()
            );
        }
    }



}
