
package com.example.tracker.controllers;

import com.example.tracker.entities.Product;
import com.example.tracker.services.IProductService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadProducts(@RequestParam("file") MultipartFile file) throws Exception {
        var products = this.productService.uploadProducts(file);

        return ResponseEntity.ok(new HashMap<>(
            Map.of(
                "message", "File uploaded successfully", 
                "products", products
                )
            )
        );
    }

    @PostMapping("/import")
    public ResponseEntity<Map<String, Object>> importProducts(@RequestBody String filePath) throws Exception {
        if (filePath == null || filePath.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new HashMap<>(Map.of("message", "File path is required")));
        }

        var products = this.productService.importProducts(filePath);
        return ResponseEntity.ok(new HashMap<>(
                        Map.of(
                                "message", "Data from local-file saved successfully",
                                "products", products
                        )
                )
        );
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAllProducts() {
        var products = this.productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

}
