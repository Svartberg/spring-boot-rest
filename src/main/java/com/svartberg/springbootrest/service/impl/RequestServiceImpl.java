package com.svartberg.springbootrest.service.impl;

import com.svartberg.springbootrest.dto.ProductRequestDTO;
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
    public ProductRequestDTO read(Long id) {

        Request request = findRequestById(id);

        List<Product> productList = productRepository.findProductsByRequestsId(id);

        return convertRequestAndProductToProductRequestDTO(request, productList);
    }

    @Override
    public ProductRequestDTO readByClientId(Long clientId, Long requestId) {

        Request request = findRequestByIdAndClientById(clientId, requestId);

        List<Product> productList = productRepository.findProductsByRequestsId(requestId);

        return convertRequestAndProductToProductRequestDTO(request, productList);
    }

    @Override
    public List<ProductRequestDTO> readAll() {

        List<Request> requestList = requestRepository.findAll();

        return requestList.stream()
                .map(e -> {
                    List<Product> productList = productRepository.findProductsByRequestsId(e.getId());
                    return convertRequestAndProductToProductRequestDTO(e, productList);})
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductRequestDTO> readAllByClientId(Long id) {

        Client client = findClientById(id);

        List<Request> requestList = requestRepository.findAllByClientOrderByIdDesc(client);

        return requestList.stream()
                .map(e -> {
                    List<Product> productList = productRepository.findProductsByRequestsId(e.getId());
                    return convertRequestAndProductToProductRequestDTO(e, productList);})
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(Long id, RequestDTO requestDTO) {

        Request request = findRequestById(id);

        Request requestUpdate = updateRequest(request, requestDTO);

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
            throw new CustomException("Request Id is not found", HttpStatus.NOT_FOUND);
        }

        return request;
    }

    private Request findRequestByIdAndClientById(Long clientId, Long requestId) {

        final Request request = findRequestById(requestId);

        if (!request.getClient().getId().equals(clientId)) {
            throw new CustomException("Client Id is not found in Request", HttpStatus.NOT_FOUND);
        }

        return request;
    }

    private Request updateRequest(Request request, RequestDTO requestDTO) {

        Request requestNew = modelMapper.map(requestDTO, Request.class);

        if (requestDTO.getSecretWord() != null) {
            request.setSecretWord(requestNew.getSecretWord());
        }
        if (requestDTO.getStatus() != null) {
            request.setStatus(requestNew.getStatus());
        }
        if (!requestDTO.getProductId().isEmpty()) {
            request.setProducts(getFilteredSetProducts(requestDTO.getProductId()));
        }

        return request;
    }

    private Client findClientById(Long id) {

        final Client client = clientRepository.findById(id).orElse(null);

        if (client == null) {
            throw new CustomException("Client Id is not found", HttpStatus.NOT_FOUND);
        }

        return client;
    }

    private Set<Product> getFilteredSetProducts(List<Long> productsId) {
        return productsId.stream()
                .map(e -> productRepository.findById(e).orElse(null))
                .collect(Collectors.toSet());
    }

    private ProductRequestDTO convertRequestAndProductToProductRequestDTO(Request request,
                                                                          List<Product> productList) {

        ProductRequestDTO productRequestDTO = modelMapper.map(request, ProductRequestDTO.class);

        productRequestDTO.setProducts(productList);

        return productRequestDTO;
    }
}
