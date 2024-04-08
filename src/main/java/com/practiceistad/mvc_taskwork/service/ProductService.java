package com.practiceistad.mvc_taskwork.service;

import com.practiceistad.mvc_taskwork.dto.ProductCreateRequest;
import com.practiceistad.mvc_taskwork.dto.ProductEditRequest;
import com.practiceistad.mvc_taskwork.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findProducts(String name, Boolean status);

    ProductResponse findProductById(Integer id);

    void createNewProduct(ProductCreateRequest productCreateRequest);

    //    void editProductByUuid(ProductEditRequest request,String uuid);
    ProductResponse editProductByUuid(String uuid, ProductEditRequest request);

    void deleteProductByUuid(String uuid);

    ProductResponse findProductByUuid(String uuid);
}
