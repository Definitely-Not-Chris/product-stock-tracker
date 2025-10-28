
package com.example.tracker.services;

import com.example.tracker.entities.Product;
import com.example.tracker.repositories.IProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final IFileParser<List<List<String>>> fileParser;

    public ProductService(IProductRepository productRepository, IFileParser<List<List<String>>> fileParser) {
        this.productRepository = productRepository;
        this.fileParser = fileParser;
    }

    public int uploadProducts(MultipartFile file) throws Exception {
        var records = this.fileParser.parse(file);

        for(List<String> record : records) {
            System.out.println(record);
        }

        return records.size();
    }

    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }
}
