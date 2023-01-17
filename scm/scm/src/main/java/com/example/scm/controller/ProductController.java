package com.example.scm.controller;

import com.example.scm.domain.Product;
import com.example.scm.dto.ProductDTO;
import com.example.scm.dto.response.SCMResponse;
import com.example.scm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize("hasRole('USER') or hasRole('RETAILER')")
    @GetMapping("/getProduct")
    public ResponseEntity<ProductDTO> searchProduct(@RequestParam("productName") String productName){
        ProductDTO productDTO = productService.searchProductByName(productName);
        return ResponseEntity.ok(productDTO);
    }

    @PreAuthorize("hasRole('RETAILER')")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PreAuthorize("hasRole('RETAILER')")
    @PostMapping("/addProduct")
    public ResponseEntity<SCMResponse> saveNewProduct(@RequestBody ProductDTO productDTO){
        productService.addNewProduct(productDTO);

        SCMResponse response = new SCMResponse("The product has been added successfully",true);
        return ResponseEntity.ok(response);
    }
}
