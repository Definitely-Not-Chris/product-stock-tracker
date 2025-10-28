
package com.example.tracker.controllers;

import com.example.tracker.services.IProductService;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> uploadProducts(@RequestParam("file") MultipartFile file) throws Exception {
        int result = this.productService.uploadProducts(file);
        return ResponseEntity.ok(new HashMap<>(Map.of("message", "File uploaded successfully", "inserted", result)));
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
