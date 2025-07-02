package com.example.ApiRestFull.mapper;

import com.example.ApiRestFull.domain.entity.Product;
import com.example.ApiRestFull.dto.request.RequestProduct;
import com.example.ApiRestFull.dto.response.ResponseProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(RequestProduct requestProduct);

    ResponseProduct toResponseProduct(Product product);

    List<ResponseProduct> toResponseProduct(List<Product> products);
}
