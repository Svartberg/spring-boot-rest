package com.svartberg.springbootrest.service.impl;

import com.svartberg.springbootrest.dto.ProductDTO;
import com.svartberg.springbootrest.exception.CustomException;
import com.svartberg.springbootrest.model.Product;
import com.svartberg.springbootrest.repository.ProductRepository;
import com.svartberg.springbootrest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public void create(ProductDTO productDTO) {

        final Product product = modelMapper.map(productDTO, Product.class);

        productRepository.save(product);

    }

    @Override
    public ProductDTO read(Long id) {
        return modelMapper.map(findProductById(id), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> readAll() {

        List<Product> productList = productRepository.findAll();

        return productList.stream()
                .map(e -> modelMapper.map(e, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(Long id, ProductDTO productDTO) {

        Product productOld = findProductById(id);

        Product productNew = modelMapper.map(productDTO, Product.class);

        Product productUpdate = updateProduct(productOld, productNew);

        productRepository.save(productUpdate);

        return true;
    }

    @Override
    public boolean delete(Long id) {

        final Product product = findProductById(id);

        productRepository.delete(product);

        return true;
    }

    private Product findProductById(Long id) {

        final Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new CustomException("Product Id is not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return product;
    }

    private Product updateProduct(Product productOld, Product productNew) {

        if (productNew.getName() != null) {
            productOld.setName(productNew.getName());
        }
        if (productNew.getWeight() != 0f) {
            productOld.setWeight(productNew.getWeight());
        }
        if (productNew.getComment() != null) {
            productOld.setComment(productNew.getComment());
        }

        return productOld;
    }
}
