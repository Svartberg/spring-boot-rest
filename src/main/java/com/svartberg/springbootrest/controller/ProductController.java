package com.svartberg.springbootrest.controller;

import com.svartberg.springbootrest.dto.ProductDTO;
import com.svartberg.springbootrest.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Product controller", description = "Контроллер продуктов. Предоставляет операции с точки зрения администратора: создание/удаление продуктов.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create new product", description  = "Создание нового продукта")
    @PostMapping("/")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductDTO productDTO) {
        productService.create(productDTO);
        return  ResponseEntity.accepted().build();
    }

    @Operation(summary = "Get all clients", description  = "Получение всех продуктов")
    @GetMapping("/")
    public ResponseEntity<?> getAllProduct() {
        List<ProductDTO> productDTOS = productService.readAll();
        return ResponseEntity.ok(productDTOS);
    }

    @Operation(summary = "Get product by ID", description = "Получение продукта по ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        ProductDTO productDTO = productService.read(id);
        return  ResponseEntity.ok(productDTO);
    }

    @Operation(summary = "Update product", description = "Обновление информации о продукте")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
        return  ResponseEntity.accepted().build();
    }

    @Operation(summary = "Delete product by id", description = "Удаление продукта по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
        return  ResponseEntity.accepted().build();
    }
}
