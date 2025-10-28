package com.example.tracker.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;


@SpringBootTest
public class ProductServiceTest {

    @Mock
    private IProductService productService;

    @Test
    void testImportProducts() throws Exception {
        var actualProducts = this.productService.findAllProducts();
        Assertions.assertEquals(0, actualProducts.size());

        var importedProducts =  this.productService.importProducts("src/main/resources/static/temp/products/stock.csv");
        var afterImportProducts = this.productService.findAllProducts();

        Assertions.assertEquals(importedProducts.size(), afterImportProducts.size());
    }

    @Test
    void testUploadProducts() throws Exception {
        String fileContent = """
            sku,name,stockQuantity
            ABC123,Widget Pro,10
            XYZ456,Gadget Plus,30
            LMN789,Tool Set,25
        """;

        byte[] contentBytes = fileContent.getBytes();

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "test-upload.csv",
                "text/plain",
                contentBytes
        );
        var actualProducts = this.productService.findAllProducts();
        Assertions.assertEquals(0, actualProducts.size());

        var uploadedProducts = this.productService.uploadProducts(mockMultipartFile);
        var afterUploadProducts = this.productService.findAllProducts();

        Assertions.assertEquals(uploadedProducts.size(), afterUploadProducts.size());
    }
}
