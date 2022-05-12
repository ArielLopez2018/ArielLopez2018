package com.example.model.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequest implements Serializable{
    
    private Long transaccionId;
    private Double amount;
    private String type;

    public ProductoRequest() {
    }

    public ProductoRequest(Long transaccionId, Double amount, String type) {
        this.transaccionId = transaccionId;
        this.amount = amount;
        this.type = type;
    }
    
}
