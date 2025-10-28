package com.example.tracker.services;

import com.example.tracker.entities.Product;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    int uploadProducts(MultipartFile var1) throws Exception;

    List<Product> findAllProducts();
}
