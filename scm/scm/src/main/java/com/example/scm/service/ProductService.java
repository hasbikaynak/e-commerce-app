package com.example.scm.service;

import com.example.scm.domain.Product;
import com.example.scm.dto.ProductDTO;
import com.example.scm.exception.ResourceNotFoundException;
import com.example.scm.repository.ProductRepository;
import com.example.scm.repository.RoleRepository;
import com.example.scm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductDTO searchProductByName(String productName){
    Product product =  productRepository.findByProductName(productName).orElseThrow(() -> new ResourceNotFoundException(String.format("Product not found with %s product name",productName)));
    ProductDTO productDTO = new ProductDTO();
    productDTO.setProductName(product.getProductName());
    productDTO.setQuantity(product.getQuantity());
    productDTO.setPrice(product.getPrice());
    return productDTO;
    }

    public void addNewProduct(ProductDTO productDto){
        List<Product> productsFound =  productRepository.findAll();
        for (Product product: productsFound) {
            if (product.getProductName().equals(productDto.getProductName())){
                throw new ResourceNotFoundException(String.format("The Product: %s already exists in the system",product.getProductName()));
            }
        }
      Product productNew = new Product();
      productNew.setProductName(productDto.getProductName());
      productNew.setPrice(productDto.getPrice());
      productNew.setQuantity(productDto.getQuantity());
        productRepository.save(productNew);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product not found with id %s",id)));
    }

    public List<Product> getAllProducts(){
       return productRepository.findAll();
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }
}
