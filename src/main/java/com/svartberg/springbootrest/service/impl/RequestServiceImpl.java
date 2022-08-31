package com.svartberg.springbootrest.service.impl;

import com.svartberg.springbootrest.dto.RequestDTO;
import com.svartberg.springbootrest.exception.CustomException;
import com.svartberg.springbootrest.model.Client;
import com.svartberg.springbootrest.model.Request;
import com.svartberg.springbootrest.repository.ClientRepository;
import com.svartberg.springbootrest.repository.RequestRepository;
import com.svartberg.springbootrest.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @Override
    public void create(RequestDTO requestDTO) {

        final Request request = modelMapper.map(requestDTO, Request.class);

        requestRepository.save(request);

    }

    @Override
    public RequestDTO read(Long id) {
        return modelMapper.map(findRequestById(id), RequestDTO.class);
    }

    @Override
    public List<RequestDTO> readAll() {

        List<Request> requestList = requestRepository.findAll();

        return requestList.stream()
                .map(e -> modelMapper.map(e, RequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> readAllByClientId(Long id) {

        Client client = findClientById(id);

        List<Request> requestList = requestRepository.findAllByClientOrderByIdDesc(client);

        return requestList.stream()
                .map(e -> modelMapper.map(e, RequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(Long id, RequestDTO requestDTO) {

        Request requestOld = findRequestById(id);

        Request requestNew = modelMapper.map(requestDTO, Request.class);

        Request requestUpdate = updateRequest(requestOld, requestNew);

        requestRepository.save(requestUpdate);

        return true;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    private Request findRequestById(Long id) {

        final Request request = requestRepository.findById(id).orElse(null);

        if (request == null) {
            throw new CustomException("Product Id is not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return request;
    }

    private Request updateRequest(Request requestOld, Request requestNew) {

        if (requestNew.getSecretWord() != null) {
            requestOld.setSecretWord(requestNew.getSecretWord());
        }
        if (requestNew.getStatus() != null) {
            requestOld.setStatus(requestNew.getStatus());
        }

        return requestOld;
    }

    private Client findClientById(Long id) {

        final Client client = clientRepository.findById(id).orElse(null);

        if(client == null) {
            throw new CustomException("Client Id is not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return client;
    }
}
