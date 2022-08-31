package com.svartberg.springbootrest.controller;

import com.svartberg.springbootrest.dto.ClientDTO;
import com.svartberg.springbootrest.dto.RequestDTO;
import com.svartberg.springbootrest.service.ClientService;
import com.svartberg.springbootrest.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    private final RequestService requestService;

    @PostMapping("/")
    public ResponseEntity<?> createNewClient(@RequestBody ClientDTO clientDTO) {
        clientService.create(clientDTO);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllClient() {
        List<ClientDTO> clientDTOS = clientService.readAll();
        return ResponseEntity.ok(clientDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id) {
        ClientDTO clientDTO = clientService.read(id);
        return ResponseEntity.ok(clientDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO) {
        clientService.update(id, clientDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        clientService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<?> getAllClientRequests(@PathVariable("id") Long id) {
        List<RequestDTO> requestDTOS = requestService.readAllByClientId(id);
        return ResponseEntity.ok(requestDTOS);
    }

    @GetMapping("/{client_id}/requests/{request_id}")
    public ResponseEntity<?> getAllClientRequests(@PathVariable("client_id") Long clientId,
                                                  @PathVariable("request_id") Long requestId ) {
        RequestDTO requestDTO = requestService.readByClientId(clientId, requestId);
        return ResponseEntity.ok(requestDTO);
    }

}
