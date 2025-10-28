package com.example.tracker.services;

import com.example.tracker.entities.Product;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    List<Product> uploadProducts(MultipartFile file) throws Exception;
    List<Product> importProducts(String filePath) throws Exception;
    List<Product> findAllProducts();
}
