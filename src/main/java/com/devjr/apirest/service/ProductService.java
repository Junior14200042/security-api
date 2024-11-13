package com.devjr.apirest.service;

import com.devjr.apirest.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> showAll();

    void save(Product product);

    Product findById(Long id);

    void updateProduct(Product product,Long id);

    void deleteByID(Long id);
}
