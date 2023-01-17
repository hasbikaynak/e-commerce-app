package com.example.scm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int productQuantity;
}
