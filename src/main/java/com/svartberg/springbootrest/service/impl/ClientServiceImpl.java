package com.svartberg.springbootrest.service.impl;

import com.svartberg.springbootrest.dto.ClientDTO;
import com.svartberg.springbootrest.exception.CustomException;
import com.svartberg.springbootrest.model.Client;
import com.svartberg.springbootrest.repository.ClientRepository;
import com.svartberg.springbootrest.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @Override
    public void create(ClientDTO clientDTO) {

        final Client client = modelMapper.map(clientDTO, Client.class);

        clientRepository.save(client);

    }

    @Override
    public ClientDTO read(Long id) {
        return modelMapper.map(findClientById(id), ClientDTO.class);
    }

    @Override
    public List<ClientDTO> readAll() {

        List<Client> clientList = clientRepository.findAll();

        return clientList.stream()
                .map(e -> modelMapper.map(e, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(Long id, ClientDTO clientDTO) {

        Client clientOld = findClientById(id);

        Client clientNew = modelMapper.map(clientDTO, Client.class);

        Client clientUpdate = updateClient(clientOld, clientNew);

        clientRepository.save(clientUpdate);

        return true;
    }

    @Override
    public boolean delete(Long id) {

        final Client client = findClientById(id);

        clientRepository.delete(client);

        return true;
    }

    private Client findClientById(Long id) {

        final Client client = clientRepository.findById(id).orElse(null);

        if(client == null) {
            throw new CustomException("Client Id is not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return client;
    }

    private Client updateClient( Client clientOld, Client clientNew) {

        if(clientNew.getName() != null) {
            clientOld.setName(clientNew.getName());
        }
        if(clientNew.getSite() != null) {
            clientOld.setSite(clientNew.getSite());
        }
        if(clientNew.getComment() != null) {
            clientOld.setComment(clientNew.getComment());
        }

        return clientOld;
    }

}
