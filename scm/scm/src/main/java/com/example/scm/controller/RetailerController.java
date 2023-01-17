package com.example.scm.controller;

import com.example.scm.domain.Product;
import com.example.scm.dto.request.UpdateProductRequest;
import com.example.scm.dto.response.SCMResponse;
import com.example.scm.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retailer/transactions")
@PreAuthorize("hasRole('RETAILER')")
public class RetailerController {

    @Autowired
    private RetailerService retailerService;

    @GetMapping("/view/products")
    public ResponseEntity<List<Product>> viewProducts(){
       return ResponseEntity.ok(retailerService.getAllProducts());
    }

    @PutMapping("/updateProducts")
    public ResponseEntity<SCMResponse> updateProducts(@RequestBody UpdateProductRequest updateProductRequest){
       retailerService.updateProductByUpdateProductRequest(updateProductRequest);

       SCMResponse response = new SCMResponse("Product has been updated",true);
       return ResponseEntity.ok(response);
    }
}
