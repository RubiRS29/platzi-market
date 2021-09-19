package com.platzi.market.domain.service;


//Servicio de dominio
//Intermediario entre el controlador y el repositorio

import com.platzi.market.domain.DomainProduct;
import com.platzi.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //Agrega una diferenciacion semantica - servicio de nuestra logica del negocio
public class ProductService {
    @Autowired //Inicialza un ProductRepository
    private ProductRepository productRepository;

    public List<DomainProduct> getAll(){
        return productRepository.getAll();
    }

    public Optional<DomainProduct> getProduct(int productId){
        return productRepository.getProduct(productId);
    }
    public Optional<List<DomainProduct>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public DomainProduct save(DomainProduct product){
        return productRepository.save(product);
    }

    public boolean delete(int productId){
        //Manada a llamar a getProducto
        //verifica con la funcion map si el producto existe
        //si existe, se elimina y retorna false
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
}
