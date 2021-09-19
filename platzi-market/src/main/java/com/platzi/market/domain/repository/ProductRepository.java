package com.platzi.market.domain.repository;

import com.platzi.market.domain.DomainProduct;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<DomainProduct> getAll();
    Optional<List<DomainProduct>> getByCategory(int categoryId);
    Optional<List<DomainProduct>> getScarseProducts(int quantity);
    Optional<DomainProduct> getProduct(int productId);
    DomainProduct save(DomainProduct product);
    void delete(int productId);

}
