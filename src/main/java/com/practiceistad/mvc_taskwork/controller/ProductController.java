package com.practiceistad.mvc_taskwork.controller;

import com.practiceistad.mvc_taskwork.dto.ProductCreateRequest;
import com.practiceistad.mvc_taskwork.dto.ProductEditRequest;
import com.practiceistad.mvc_taskwork.dto.ProductResponse;
import com.practiceistad.mvc_taskwork.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {
    private final ProductService productService;

    //    @PostMapping//Create
//    @ResponseStatus(HttpStatus.CREATED)
//    void createNewProduct(@RequestBody ProductCreateRequest request) {
//        productService.createNewProduct(request);
//    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request) {
        System.out.println("REQUEST: " + request);
        productService.createNewProduct(request);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
//Delete
    void deleteByUuid(@PathVariable String uuid) {
        productService.deleteProductByUuid(uuid);
    }


    @PutMapping("/{uuid}")
    ProductResponse editProductByUuid(@PathVariable String uuid, @Valid @RequestBody ProductEditRequest request ){
        return productService.editProductByUuid(uuid,request);
    }
    @GetMapping("/uuid/{uuid}")
    ProductResponse findProductByUuid(@PathVariable String uuid){
        return productService.findProductByUuid(uuid);
    }
//Update
//    void editProductByUuid(@PathVariable String uuid, @RequestBody ProductEditRequest request) {
//        productService.editProductByUuid(request, uuid);
//    }

    @GetMapping
//Find Product
    ResponseEntity<?> findProducts(@RequestParam(required = false, defaultValue = "") String name,
                                   @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return new ResponseEntity<>(Map.of(
                "message", "Products have been found",
                "data", productService.findProducts(name, status)
        ), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
//Find by ID
    Map<String, Object> findProductById(@PathVariable Integer id) {
        log.info("Hello World...!");
        return Map.of(
                "data", productService.findProductById(id)
        );
    }

}
