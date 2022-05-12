package com.example.model.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SumDto implements Serializable {
    
    private Double sum = 0.0;
    
    public void sum(Double value) {
        this.sum = this.sum + value;
    }
}
