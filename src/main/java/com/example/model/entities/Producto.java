package com.example.model.entities;

import com.example.model.request.ProductoRequest;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author AL_I01971
 */
@Entity
@Table(name = "PRODUCTO")
@Getter
@Setter
public class Producto implements Serializable{
    
    @Id
    @Column(name = "TRANSACCION_ID")
//    @GeneratedValue
    private Long transaccionId;
    
    @Column(name = "AMOUNT")
    private Double amount;
    
    @Column(name = "TYPE")
    private String type;
    
    @Column(name = "PARENT_ID")
    private Long parentId;

    public Producto() {
    }
    
    public Producto(Long transaccionId, Double amountd, String typed) {
        this.transaccionId = transaccionId;
        this.amount = amountd;
        this.type = typed;
    }

    public Producto(Long transaccionId, Double amountd, String typed, Long parentId) {
        this.transaccionId = transaccionId;
        this.amount = amountd;
        this.type = typed;
        this.parentId = parentId;
    }
    
}
