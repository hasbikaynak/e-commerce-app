package com.example.scm.service;

import com.example.scm.domain.Product;
import com.example.scm.dto.request.UpdateProductRequest;
import com.example.scm.exception.ResourceNotFoundException;
import com.example.scm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;

@Service
public class RetailerService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void updateProductByUpdateProductRequest(UpdateProductRequest updateProductRequest){
        List<Product> products=  productService.getAllProducts();
        try {
            for (Product product: products) {
            if (product.getProductName().equals(updateProductRequest.getProductName())){
                product.setQuantity(updateProductRequest.getQuantity());
                productService.saveProduct(product);
            }
        }
        }catch(ResourceNotFoundException e){
            String.format("Product not found with name of %s",updateProductRequest.getProductName());
        }


//        throw new ResourceNotFoundException(String.format("Product not found with name of %s",updateProductRequest.getProductName()));
    }
}
