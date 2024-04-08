package com.practiceistad.mvc_taskwork;
import com.practiceistad.mvc_taskwork.model.Product;
import com.practiceistad.mvc_taskwork.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MvcApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void findByName(){
        List<Product> foundProduct=productRepository.findByName("coca");
        System.out.println("found product:"+foundProduct.size());
    }
    @Test
    void findByNameContainsIgnoreCaseAndStatus(){
        List<Product> foundProduct = productRepository.findByNameContainsIgnoreCaseAndStatus("ca",true);
        System.out.println("Found product by name and status:"+foundProduct.size());
    }
    @Test
    void findByQtyGreaterThan(){
        List<Product> foundProductGreaterThen = productRepository.findByQtyGreaterThan(10);
        System.out.println("greater than 10 have :"+foundProductGreaterThen.size()+" product");
    }
    @Test
    void findByNameContainsIgnoreCase(){
        List<Product> foundProduct = productRepository.findByNameContainsIgnoreCase("ca");
        System.out.println("Found product by name and status:"+foundProduct.size());
    }


}