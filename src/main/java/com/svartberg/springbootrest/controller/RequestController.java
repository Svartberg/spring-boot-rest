package com.svartberg.springbootrest.controller;

import com.svartberg.springbootrest.dto.ClientDTO;
import com.svartberg.springbootrest.dto.RequestDTO;
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

    @PostMapping("/")
    public ResponseEntity<?> createNewRequest(@RequestBody RequestDTO requestDTO) {
        requestService.create(requestDTO);
        return  ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllRequest() {
        List<RequestDTO> requestDTOS = requestService.readAll();
        return ResponseEntity.ok(requestDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRequestById(@PathVariable("id") Long id) {
        RequestDTO requestDTO = requestService.read(id);
        return  ResponseEntity.ok(requestDTO);
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

        return  ResponseEntity.ok(new ClientDTO());
    }
}
