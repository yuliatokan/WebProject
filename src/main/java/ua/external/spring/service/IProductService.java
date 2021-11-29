package ua.external.spring.service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ua.external.spring.dto.ProductDTO;
import ua.external.spring.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    @Transactional
    boolean createProduct(ProductDTO product);

    @Transactional(readOnly = true)
    List<ProductDTO> findAllProductsForUser(Long userId);

    @Transactional
    boolean updateProduct(ProductDTO product);

    @Transactional
    boolean deleteProductById(Long id);

    @Transactional
    List<ProductDTO> findAllProductsByNameForUser(Long userId, String name);

    int getNumberOfProducts(Long userId);

    Optional<Product> findProductById(Long id);

    @Transactional
    List<ProductDTO> getProductsByPageable(Long userId, Pageable pageable);
}
