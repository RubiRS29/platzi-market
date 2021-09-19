package com.platzi.market.web.controller;

import com.platzi.market.domain.DomainProduct;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code=200, message = "ok")
    public ResponseEntity<List<DomainProduct>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<DomainProduct> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7")
                                                        @PathVariable("id") int id){
        return productService.getProduct(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<DomainProduct>> getByCategory(@PathVariable("id") int id){
        return productService.getByCategory(id)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<DomainProduct> save(@RequestBody DomainProduct product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<DomainProduct> update(@RequestBody DomainProduct product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id){
        if (productService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
