package com.example.model.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OkDto implements Serializable{
    
    private String status;

    public OkDto(String status) {
        this.status = status;
    }
    
}
