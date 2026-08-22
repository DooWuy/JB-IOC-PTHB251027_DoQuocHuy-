package ra.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.productservice.entity.Product;
import ra.productservice.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ko tim thay san pham "));
    }
}
