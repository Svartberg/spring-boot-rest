package com.svartberg.springbootrest.controller;

import com.svartberg.springbootrest.dto.ProductDTO;
import com.svartberg.springbootrest.dto.ProductRequestDTO;
import com.svartberg.springbootrest.dto.RequestDTO;
import com.svartberg.springbootrest.service.ProductService;
import com.svartberg.springbootrest.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Requests controller", description = "Контроллер заявок. Предоставляет операции с точки зрения администратора: создание/удаление заявок.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    private final ProductService productService;

    @Operation(summary = "Create new request", description = "Создание новой заявки")
    @PostMapping("/")
    public ResponseEntity<?> createNewRequest(@RequestBody RequestDTO requestDTO) {
        requestService.create(requestDTO);
        return  ResponseEntity.accepted().build();
    }

    @Operation(summary = "Get all request", description = "Получение всех заявок")
    @GetMapping("/")
    public ResponseEntity<?> getAllRequest() {
        List<ProductRequestDTO> productRequestDTOS = requestService.readAll();
        return ResponseEntity.ok(productRequestDTOS);
    }

    @Operation(summary = "Get request by ID", description = "Получение заявки по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRequestById(@PathVariable("id") Long id) {
        ProductRequestDTO productRequestDTO = requestService.read(id);
        return  ResponseEntity.ok(productRequestDTO);
    }

    @Operation(summary = "Update request", description = "Обновление информации заявки")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRequest(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO) {
        requestService.update(id, requestDTO);
        return  ResponseEntity.accepted().build();
    }

    @Operation(summary = "Delete request by ID", description = "Удаление заявки")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable("id") Long id) {
        requestService.delete(id);
        return  ResponseEntity.accepted().build();
    }

    @Operation(summary = "Get request products", description = "Получение всех продуктов одной заявки")
    @GetMapping("/{id}/products")
    public ResponseEntity<?> getAllRequestProducts(@PathVariable("id") Long id) {
        List<ProductDTO> productDTOS = productService.readAllByRequestId(id);
        return  ResponseEntity.ok(productDTOS);
    }
}
