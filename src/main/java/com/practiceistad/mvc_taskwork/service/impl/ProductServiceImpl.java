package com.practiceistad.mvc_taskwork.service.impl;

import com.practiceistad.mvc_taskwork.dto.CategoryResponse;
import com.practiceistad.mvc_taskwork.dto.ProductCreateRequest;
import com.practiceistad.mvc_taskwork.dto.ProductEditRequest;
import com.practiceistad.mvc_taskwork.dto.ProductResponse;
import com.practiceistad.mvc_taskwork.model.Category;
import com.practiceistad.mvc_taskwork.model.Product;
import com.practiceistad.mvc_taskwork.repository.ProductRepository;
import com.practiceistad.mvc_taskwork.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private List<Product> products;
    private final ProductRepository productRepository;
//    public ProductServiceImpl(){
//        products = new ArrayList<>();
//        Product p1 = new Product();
//        p1.setId(1);
//        p1.setName("Mengseu");
//        p1.setPrice(1.00);
//        p1.setQty(1);
//        p1.setUuid(UUID.randomUUID().toString());
//        p1.setImportedDate(LocalDateTime.now());
//        p1.setStatus(true);
//        Product p2 = new Product();
//        p2.setId(2);
//        p2.setName("seu");
//        p2.setPrice(1.00);
//        p2.setQty(1);
//        p2.setUuid(UUID.randomUUID().toString());
//        p2.setImportedDate(LocalDateTime.now());
//        p2.setStatus(true);
//        products.add(p1);
//        products.add(p2);
//    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid);
        if (product==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product didn't found");
        }
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(),product.getQty());
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
//        Product newProduct = new Product() ;
//        newProduct.setName(productCreateRequest.name());
//        newProduct.setPrice(productCreateRequest.price());
//        newProduct.setQty(productCreateRequest.qty());
//        newProduct.setId(products.size()+1);
//        newProduct.setUuid(UUID.randomUUID().toString());
//        newProduct.setImportedDate(LocalDateTime.now());
//        newProduct.setStatus(true);
//        products.add(newProduct)
        Product newProduct = new Product();
        newProduct.setName(request.name());
        newProduct.setPrice(request.price());
        newProduct.setQty(request.qty());
        newProduct.setUuid(UUID.randomUUID().toString());
        newProduct.setImportedDate(LocalDateTime.now());
        newProduct.setStatus(true);
        productRepository.save(newProduct);

    }

//    @Override
//    public void editProductByUuid(ProductEditRequest request, String uuid) {
//        Check UUID Exists
//        long count = products.stream()
//                .filter(product -> product.getUuid().equals(uuid))
//                .peek(oldProduct -> {
//                    oldProduct.setName(request.name());
//                    oldProduct.setPrice(request.price());
//                }).count();
//        System.out.println("Affected Row= " + count);
//        Product product = productRepository.findByUuid(uuid)
//        if(!productRepository.existsByUuid(uuid)){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,
//                    "Product not found!"
//            );
//        }
//        product.setName(request.name());
//        product.setPrice(request.price());
//        productRepository.save(product);
//    }

    @Override
    public ProductResponse editProductByUuid(String uuid, ProductEditRequest request) {
        Product product = productRepository.findByUuid(uuid);
        if(!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found!"
            );
        }
        product.setName(request.name());
        product.setPrice(request.price());
        productRepository.save(product);
        return this.findProductByUuid(uuid);
    }

    @Override
    @Transactional
    public void deleteProductByUuid(String uuid) {
//        products.removeIf(p -> p.getUuid().equals(uuid));
        if(!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found!"
            );
        }
        productRepository.deleteByUuid(uuid);
    }

    @Override
    public List<ProductResponse> findProducts(String name, Boolean status) {
//        return products.stream()
//                .filter(product -> product.getName().toLowerCase()
//                        .contains(name.toLowerCase()) && product.getStatus().equals(status))
//                .map(product -> new ProductResponse(
//                        product.getUuid(),
//                        product.getName(),
//                        product.getPrice(),
//                        product.getQty()
//                ))
//                .toList();
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .toList();
    }


    @Override
    public ProductResponse findProductById(Integer id) {
//        return products.stream()
//                .filter(product -> product.getStatus() && product.getId().equals(id))
//                .map(product -> new ProductResponse(
//                        product.getUuid(),
//                        product.getName(),
//                        product.getPrice(),
//                        product.getQty()
//                ))
//                .findFirst()
//                .orElse(null);
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Not Found"
        ));
        return new ProductResponse(product.getUuid(), product.getName(), product.getPrice(), product.getQty());
    }

}
