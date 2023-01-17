package com.example.scm.controller;

import com.example.scm.domain.Bill;
import com.example.scm.dto.BillDTO;
import com.example.scm.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
@PreAuthorize("hasRole('RETAILER')")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("/generate")
    public ResponseEntity<Bill> getBilling(@RequestBody BillDTO billDTO){
       Bill  bill = billService.generateBilling(billDTO);

        return ResponseEntity.ok(bill);
    }
}
