package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Product, Integer>{
    List<Product> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Product>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
