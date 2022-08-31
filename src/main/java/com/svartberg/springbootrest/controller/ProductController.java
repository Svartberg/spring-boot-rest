package com.svartberg.springbootrest.controller;

import com.svartberg.springbootrest.dto.ProductDTO;
import com.svartberg.springbootrest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductDTO productDTO) {
        productService.create(productDTO);
        return  ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProduct() {
        List<ProductDTO> productDTOS = productService.readAll();
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        ProductDTO productDTO = productService.read(id);
        return  ResponseEntity.ok(productDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
        return  ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
        return  ResponseEntity.accepted().build();
    }
}
