package com.example.services;

import com.example.model.request.ProductoRequest;
import com.example.model.request.ProductoWhitParentRequest;
import com.example.model.response.SumDto;
import java.util.List;

/**
 *
 * @author AL_I01971
 */
public interface ProductoService {
    
    public void validateRequestNewTransaction(ProductoRequest productoRequest) throws IllegalArgumentException;
    public void createTransaction(ProductoRequest productoRequest);
    
    public void validateRequestNewTransaction(ProductoWhitParentRequest productoRequest) throws IllegalArgumentException;
    public void createTransaction(ProductoWhitParentRequest productoRequest);
    
    public List<Long> getTransactionIds(String type);
    
    public SumDto getSumTransactions(Long transactionsId);
    
}
