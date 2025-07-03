package com.example.ApiRestFull.domain.service;

import com.example.ApiRestFull.domain.entity.Product;
import com.example.ApiRestFull.domain.repository.ProductDAO;
import com.example.ApiRestFull.dto.request.RequestProduct;
import com.example.ApiRestFull.dto.response.ResponseProduct;
import com.example.ApiRestFull.exception.NotFoundException;
import com.example.ApiRestFull.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDAO;
    private final ProductMapper productMapper;

    public ProductService(ProductDAO productDAO, ProductMapper productMapper) {
        this.productDAO = productDAO;
        this.productMapper = productMapper;
    }

    @Transactional
    public ResponseProduct save(RequestProduct requestProduct) {
        Product product = this.productDAO.save(productMapper.toProduct(requestProduct));
        return productMapper.toResponseProduct(product);
    }

    public List<ResponseProduct> findAllProducts() {
       return productMapper.toResponseProduct(productDAO.findAll());
    }

    @Transactional
    public ResponseProduct updateProduct(RequestProduct requestProduct) {
        Product product = this.productDAO.findById(requestProduct.id()).orElseThrow(() ->
                new NotFoundException("Product with ID: "+requestProduct.id()+" not found"));

        product.setName(requestProduct.name());
        product.setDescription(requestProduct.description());
        product.setPrice(Double.parseDouble(requestProduct.price()));
        product.setQuantity(requestProduct.quantity());
        product.setCategory(requestProduct.category());
        product.setActive(Boolean.parseBoolean(requestProduct.active()));

        return productMapper.toResponseProduct(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productDAO.findById(id).orElseThrow(() ->
                new NotFoundException("Product with ID: "+ id +" not found"));
        productDAO.delete(product);
    }
}
