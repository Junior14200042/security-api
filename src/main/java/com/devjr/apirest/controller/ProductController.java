package com.devjr.apirest.controller;

import com.devjr.apirest.model.Product;
import com.devjr.apirest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public List<Product> showAll(){

        return productService.showAll();
    }
    @PostMapping()
    public void Save(@RequestBody Product product){
        productService.save(product);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){

        return productService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){

        productService.deleteByID(id);
    }
    @PutMapping("/{id}")
    public void updateById(@RequestBody Product product,@PathVariable Long id){
        productService.updateProduct(product,id);
    }

}
