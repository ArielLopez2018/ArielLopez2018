package com.example.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoWhitParentRequest extends ProductoRequest{
    private Long parentId;

    public ProductoWhitParentRequest(Long transaccionId, Double amount, String type, Long parentId) {
        super(transaccionId, amount, type);
        this.parentId = parentId;
    }
    
}
