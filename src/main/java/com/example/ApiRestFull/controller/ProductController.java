package com.example.ApiRestFull.controller;

import com.example.ApiRestFull.domain.service.ProductService;
import com.example.ApiRestFull.dto.request.RequestProduct;
import com.example.ApiRestFull.dto.response.ResponseProduct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity getAllProduct() {
        List<ResponseProduct> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid RequestProduct requestProduct) {
        ResponseProduct responseProduct = this.productService.save(requestProduct);

        return ResponseEntity.created(URI.create("/product/"+ responseProduct.getId())).body(responseProduct);
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct requestProduct) {
        ResponseProduct responseProduct = this.productService.updateProduct(requestProduct);
        return ResponseEntity.ok().body(responseProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
