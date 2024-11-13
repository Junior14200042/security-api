package com.devjr.apirest.service;

import com.devjr.apirest.model.Product;
import com.devjr.apirest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> showAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
         productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {

        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return product.get();
        }

        return product.orElseThrow();
    }

    @Override
    public void updateProduct(Product product, Long id) {

        Optional<Product> product1 = productRepository.findById(id);

        if(product1.isPresent()){

            Product productUpdate = product1.get();

            productUpdate.setName(product.getName());
            productUpdate.setPrice(product.getPrice());
            productUpdate.setQuantity(product.getQuantity());
            productUpdate.setDescription(product.getDescription());

            productRepository.save(productUpdate);
        }

    }

    @Override
    public void deleteByID(Long id) {

        productRepository.deleteById(id);

    }
}
