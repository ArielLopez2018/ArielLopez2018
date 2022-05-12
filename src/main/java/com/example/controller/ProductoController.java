package com.example.controller;

import com.example.model.request.ProductoRequest;
import com.example.model.request.ProductoWhitParentRequest;
import com.example.model.response.OkDto;
import com.example.model.response.SumDto;
import com.example.services.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AL_I01971
 */
@RestController
@RequestMapping("/transactions")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @PutMapping("/{transaccionId}")
    public ResponseEntity<Object> newTransaction(@PathVariable(required = true) Long transaccionId, @RequestBody ProductoWhitParentRequest productoRequest) {
        productoRequest.setTransaccionId(new Long(transaccionId));
        if (productoRequest.getParentId() == null ) {
            ProductoRequest request = new ProductoRequest(productoRequest.getTransaccionId(), productoRequest.getAmount(), productoRequest.getType());
            productoService.validateRequestNewTransaction(request);
            productoService.createTransaction(request);
        } else {
            productoService.validateRequestNewTransaction(productoRequest);
            productoService.createTransaction(productoRequest);
        }
        return new ResponseEntity<>(new OkDto("OK"), HttpStatus.OK);
    }
    
    @GetMapping("/types/{types}")
    public ResponseEntity<Object> getTypes(@PathVariable(required = true) String types) {
        List<Long> productoIds = productoService.getTransactionIds(types);
        return new ResponseEntity<>(productoIds, HttpStatus.OK);
    }
    
    @GetMapping("/sum/{transaccionId}")
    public ResponseEntity<Object> getSumTransaccions(@PathVariable(required = true) Long transaccionId) {
        SumDto sum = productoService.getSumTransactions(transaccionId);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }
    
}
