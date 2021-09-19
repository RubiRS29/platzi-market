package com.platzi.market.persistence;

import com.platzi.market.domain.DomainProduct;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Product;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired //Se crean instancias
    private ProductoCrudRepository productoCrudRepository;

    @Autowired //Se crean instancias
    private ProductMapper mapper;

    //RECUPERAR TODOS LOS DATOS DE LA BD
    @Override
    public List<DomainProduct> getAll() {
        List<Product> productos = (List<Product>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<DomainProduct>> getByCategory(int categoryId) {
        List<Product> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<DomainProduct>> getScarseProducts(int quantity) {
        Optional<List<Product>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<DomainProduct> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public DomainProduct save(DomainProduct product) {
        Product producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

}
