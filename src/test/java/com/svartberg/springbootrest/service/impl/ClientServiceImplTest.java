package com.svartberg.springbootrest.service.impl;

import com.svartberg.springbootrest.dto.ClientDTO;
import com.svartberg.springbootrest.model.Client;
import com.svartberg.springbootrest.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    private final static Long CLIENT_ID = 1L;

    @Mock
    ClientRepository clientRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ClientServiceImpl clientService;

    @Test
    public void test_create() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Svartberg");
        clientDTO.setComment("Test Comment");
        clientDTO.setSite("www.google.com");

        Client client = new Client();
        client.setName("Svartberg");
        client.setComment("Test Comment");
        client.setSite("www.google.com");

        when(modelMapper.map(eq(clientDTO), eq(Client.class))).thenReturn(client);

        clientService.create(clientDTO);

        verify(modelMapper).map(eq(clientDTO), eq(Client.class));
        verify(modelMapper, times(1)).map(eq(clientDTO), eq(Client.class));

        verify(clientRepository).save(eq(client));
        verify(clientRepository, times(1)).save(eq(client));
    }

    @Test
    public void test_read_Should_Return_ClientDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Svartberg");
        clientDTO.setComment("Test Comment");
        clientDTO.setSite("www.google.com");

        Client client = new Client();
        client.setName("Svartberg");
        client.setComment("Test Comment");
        client.setSite("www.google.com");

        when(clientRepository.findById(CLIENT_ID)).thenReturn(Optional.of(client));

        when(modelMapper.map(eq(clientDTO), eq(Client.class))).thenReturn(client);

        ClientDTO result = clientService.read(CLIENT_ID);

        verify(modelMapper).map(eq(client), eq(ClientDTO.class));
        verify(modelMapper, times(1)).map(eq(client), eq(ClientDTO.class));

        verify(clientRepository).findById(CLIENT_ID);
        verify(clientRepository, times(1)).findById(CLIENT_ID);

        System.out.println(clientService.read(CLIENT_ID));
    }

    @Test
    public void readAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}