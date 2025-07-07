package com.example.ApiRestFull.mapper;

import com.example.ApiRestFull.domain.entity.Product;
import com.example.ApiRestFull.dto.request.RequestProduct;
import com.example.ApiRestFull.dto.request.RequestUpdateProduct;
import com.example.ApiRestFull.dto.response.ResponseProduct;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(RequestProduct requestProduct);

    ResponseProduct toResponseProduct(Product product);

    List<ResponseProduct> toResponseProduct(List<Product> products);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromRequest(RequestUpdateProduct requestUpdateProduct, @MappingTarget Product product);
}
