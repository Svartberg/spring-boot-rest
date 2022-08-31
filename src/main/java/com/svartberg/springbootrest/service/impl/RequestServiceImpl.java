package com.svartberg.springbootrest.service.impl;

import com.svartberg.springbootrest.dto.RequestDTO;
import com.svartberg.springbootrest.exception.CustomException;
import com.svartberg.springbootrest.model.Client;
import com.svartberg.springbootrest.model.Product;
import com.svartberg.springbootrest.model.Request;
import com.svartberg.springbootrest.repository.ClientRepository;
import com.svartberg.springbootrest.repository.ProductRepository;
import com.svartberg.springbootrest.repository.RequestRepository;
import com.svartberg.springbootrest.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    private final ClientRepository clientRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public void create(RequestDTO requestDTO) {

        Request request = modelMapper.map(requestDTO, Request.class);

        Date date = new Date();

        request.setClient(findClientById(requestDTO.getClientId()));

        request.setProducts(getFilteredSetProducts(requestDTO.getProductId()));

        request.setDate(date);

        requestRepository.save(request);

    }

    @Override
    public RequestDTO read(Long id) {
        return modelMapper.map(findRequestById(id), RequestDTO.class);
    }

    @Override
    public RequestDTO readByClientId(Long clientId, Long requestId) {
        return modelMapper.map(findRequestByIdAndClientById(clientId, requestId), RequestDTO.class);
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

        final Request request = findRequestById(id);

        requestRepository.delete(request);

        return true;
    }

    private Request findRequestById(Long id) {

        final Request request = requestRepository.findById(id).orElse(null);

        if (request == null) {
            throw new CustomException("Request Id is not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return request;
    }

    private Request findRequestByIdAndClientById(Long clientId, Long requestId) {

        final Request request = findRequestById(requestId);

        if (!request.getClient().getId().equals(clientId)) {
            throw new CustomException("Client Id is not found in Request", HttpStatus.UNPROCESSABLE_ENTITY);
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

        if (client == null) {
            throw new CustomException("Client Id is not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return client;
    }

    private Set<Product> getFilteredSetProducts(List<Long> productsId) {
        return productsId.stream()
                .map(e -> productRepository.findById(e).orElse(null))
                .collect(Collectors.toSet());
    }
}
