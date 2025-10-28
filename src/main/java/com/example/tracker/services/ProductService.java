
package com.example.tracker.services;

import com.example.tracker.entities.Product;
import com.example.tracker.repositories.IProductRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
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


    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    public List<Product> uploadProducts(MultipartFile file) throws Exception {
        var reader = this.processUploadedFile(file);
        var records = this.fileParser.parse(reader);
        var products = this.mapToProducts(records);

        return this.saveProducts(products);
    }

    public List<Product> importProducts(String filePath) throws Exception {
        var reader = this.processFile(filePath);
        var records = this.fileParser.parse(reader);
        var products = this.mapToProducts(records);

        return this.saveProducts(products);
    }

    //saveProducts function accepts list of products then iterates over it and
    // checks if product SKU already exists in database then update existing product else save new product
    private List<Product> saveProducts(List<Product> products) {
        var savedProducts = new ArrayList<Product>();

        for(var product : products) {
            var existingProduct = this.productRepository.findAllBySku(product.getSku());

            if(existingProduct == null) {
                var createdProduct = this.productRepository.save(product);
                savedProducts.add(createdProduct);
            } else {
                existingProduct.setStockQuantity(product.getStockQuantity());
                existingProduct.setName(product.getName());

                var updatedProduct = this.productRepository.save(existingProduct);
                savedProducts.add(updatedProduct);
            }
        }

        return savedProducts;
    }

    private Reader processFile(String filePath) throws Exception {
        Path path = Path.of(filePath);
        File file = path.toFile();

        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        if (!filePath.toLowerCase().endsWith(".csv")) {
            throw new IllegalArgumentException("Invalid file type. Only CSV files are allowed. ");
        }

        return new FileReader(file);
    }

    private Reader processUploadedFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File should not be empty");
        }
        if (!file.getOriginalFilename().toLowerCase().endsWith(".csv")) {
            throw new IllegalArgumentException("Invalid file type. Only CSV files are allowed.");
        }

        return new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
    }


    //FOR-IMPROVEMENT: create ProductMapper class to map csv records to products avoiding hard-dependency
    //FOR-IMPROVEMENT: handle numeric cell data as well

    //mapToProducts function accepts 2d array cells from csv file treated as string then converted to Array of product
    private List<Product> mapToProducts(List<List<String>> records) {
        var products = new ArrayList<Product>();

        for(List<String> record : records) {
            products.add(
                    new Product(
                        record.get(0).toUpperCase(),
                        record.get(1),
                        Integer.parseInt(record.get(2))
                    )
            );
        }
        return products;
    }
}
