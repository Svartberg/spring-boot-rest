package com.svartberg.springbootrest.controller;

import com.svartberg.springbootrest.dto.ClientDTO;
import com.svartberg.springbootrest.dto.ProductRequestDTO;
import com.svartberg.springbootrest.service.ClientService;
import com.svartberg.springbootrest.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client controller", description = "Контроллер клиента. Предоставляет операции с точки зрения клиента: создание/удаление клиентов, создание новой заявки клиента, добавление прокутов в заявку клиента.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    private final RequestService requestService;

    @Operation(summary = "Create new client", description = "Создание нового клиента.")
    @PostMapping("/")
    public ResponseEntity<?> createNewClient(@RequestBody ClientDTO clientDTO) {
        clientService.create(clientDTO);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Get all clients", description = "Получение всех клиентов.")
    @GetMapping("/")
    public ResponseEntity<?> getAllClient() {
        List<ClientDTO> clientDTOS = clientService.readAll();
        return ResponseEntity.ok(clientDTOS);
    }

    @Operation(summary = "Get client by ID", description = "Получение клиента по его ID.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id) {
        ClientDTO clientDTO = clientService.read(id);
        return ResponseEntity.ok(clientDTO);
    }

    @Operation(summary = "Update client", description = "Обновление информации о клиенте.")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO) {
        clientService.update(id, clientDTO);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Delete client by id", description = "Удаление клиента по его ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        clientService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Get client requests", description = "Получение всех заявок клиента.")
    @GetMapping("/{id}/requests")
    public ResponseEntity<?> getAllClientRequests(@PathVariable("id") Long id) {
        List<ProductRequestDTO> productRequestDTOS = requestService.readAllByClientId(id);
        return ResponseEntity.ok(productRequestDTOS);
    }

    @Operation(summary = "Get client's request be request id", description = "Получение заявки текущего клиента по ID заявки")
    @GetMapping("/{client_id}/requests/{request_id}")
    public ResponseEntity<?> getAllClientRequests(@PathVariable("client_id") Long clientId,
                                                  @PathVariable("request_id") Long requestId ) {
        ProductRequestDTO productRequestDTO = requestService.readByClientId(clientId, requestId);
        return ResponseEntity.ok(productRequestDTO);
    }

}
