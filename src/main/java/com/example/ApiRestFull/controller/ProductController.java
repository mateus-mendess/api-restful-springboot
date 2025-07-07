package com.example.ApiRestFull.controller;

import com.example.ApiRestFull.domain.service.ProductService;
import com.example.ApiRestFull.dto.request.RequestProduct;
import com.example.ApiRestFull.dto.request.RequestUpdateProduct;
import com.example.ApiRestFull.dto.response.ResponseProduct;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(responseProduct);
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct requestProduct) {
        ResponseProduct responseProduct = this.productService.updateProduct(requestProduct);
        return ResponseEntity.ok().body(responseProduct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchProduct(@RequestBody RequestUpdateProduct requestUpdateProduct, @PathVariable Long id) {
        productService.updatePartialProduct(requestUpdateProduct, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
