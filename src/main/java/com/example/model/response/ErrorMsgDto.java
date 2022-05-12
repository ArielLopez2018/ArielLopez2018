package com.example.model.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMsgDto implements Serializable {
    
    private String codigo;
    private String descripcion;

    public ErrorMsgDto(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
}
