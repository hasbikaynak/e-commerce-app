package com.example.scm.service;

import com.example.scm.domain.Bill;
import com.example.scm.domain.Product;
import com.example.scm.dto.BillDTO;
import com.example.scm.dto.ProductDTO;
import com.example.scm.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductService productService;

  public Bill generateBilling(BillDTO billDTO){
     List<Product> productList = productService.getAllProducts();
     Bill bill = new Bill();
     productList.forEach(product -> {
         if (product.getProductName().equals(billDTO.getProductName())){
             bill.setProduct(product);
             bill.setProductName(billDTO.getProductName());
             bill.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(billDTO.getProductQuantity())));
             bill.setProductPrice(product.getPrice());
             bill.setProductQuantity(billDTO.getProductQuantity());
             product.setQuantity(product.getQuantity()-billDTO.getProductQuantity());
             productService.saveProduct(product);
         }
     });

     billRepository.save(bill);
     return bill;
  }
}
