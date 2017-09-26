package com.emusicstore.dao;

import com.emusicstore.model.Product;

import java.util.List;

public interface ProductDao {

    void addProduct(Product product);
    void editProduct(Product product);
    Product getProductById(int ProductId);
    List<Product> getProductList();
    void deleteProduct(Product product);
}