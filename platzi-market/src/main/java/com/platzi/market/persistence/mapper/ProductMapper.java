package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.DomainProduct;
import com.platzi.market.persistence.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "activate"),
            @Mapping(source = "categoria", target = "category")
    })
    DomainProduct toProduct(Product producto);

    List<DomainProduct> toProducts(List<Product> productos);

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Product toProducto(DomainProduct product);
}
