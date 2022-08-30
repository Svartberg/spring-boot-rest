package com.svartberg.springbootrest.controller;

import com.svartberg.springbootrest.dto.ClientDTO;
import com.svartberg.springbootrest.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/")
    public ResponseEntity<?> createNewClient(@RequestBody ClientDTO clientDTO) {
        clientService.create(clientDTO);
        return  ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id) {
        return  ResponseEntity.ok(new ClientDTO());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllClient() {
        return  ResponseEntity.ok(new ClientDTO());
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateClient(@RequestParam(value="id") Long id, @RequestBody ClientDTO clientDTO) {
        return  ResponseEntity.ok(new ClientDTO());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteClient(@RequestParam(value="id") Long id) {
        return  ResponseEntity.ok(new ClientDTO());
    }
}
