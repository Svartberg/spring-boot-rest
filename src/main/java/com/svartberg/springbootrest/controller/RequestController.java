package com.svartberg.springbootrest.controller;

import com.svartberg.springbootrest.dto.ProductDTO;
import com.svartberg.springbootrest.dto.ProductRequestDTO;
import com.svartberg.springbootrest.dto.RequestDTO;
import com.svartberg.springbootrest.service.ProductService;
import com.svartberg.springbootrest.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    private final ProductService productService;

    @PostMapping("/")
    public ResponseEntity<?> createNewRequest(@RequestBody RequestDTO requestDTO) {
        requestService.create(requestDTO);
        return  ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllRequest() {
        List<ProductRequestDTO> productRequestDTOS = requestService.readAll();
        return ResponseEntity.ok(productRequestDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRequestById(@PathVariable("id") Long id) {
        ProductRequestDTO productRequestDTO = requestService.read(id);
        return  ResponseEntity.ok(productRequestDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRequest(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO) {
        requestService.update(id, requestDTO);
        return  ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable("id") Long id) {
        requestService.delete(id);
        return  ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getAllRequestProducts(@PathVariable("id") Long id) {
        List<ProductDTO> productDTOS = productService.readAllByRequestId(id);
        return  ResponseEntity.ok(productDTOS);
    }
}
