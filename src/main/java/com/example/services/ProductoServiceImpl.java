package com.example.services;

import com.example.model.entities.Producto;
import com.example.model.request.ProductoRequest;
import com.example.model.request.ProductoWhitParentRequest;
import com.example.model.response.SumDto;
import com.example.repositorys.ProductoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 *
 * @author AL_I01971
 */
@Service
public class ProductoServiceImpl implements ProductoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoServiceImpl.class);
    
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void validateRequestNewTransaction(ProductoRequest productoRequest) throws IllegalArgumentException {
        Assert.notNull(productoRequest.getAmount(), "Campo amount es requerido.");
        Assert.notNull(productoRequest.getType(), "Campo type es requerido.");
        Assert.isTrue( !productoRequest.getType().isBlank(), "Campo type es requerido.");
        Assert.isTrue( !productoRepository.findById(productoRequest.getTransaccionId()).isPresent(), "El transaccionId informado ya existe.");
        LOGGER.info("Validaciones de ProductoRequest correstas.");
    }

    @Override
    public void createTransaction(ProductoRequest productoRequest) {
        Producto producto = new Producto(productoRequest.getTransaccionId(), productoRequest.getAmount(), productoRequest.getType());
        productoRepository.save(producto);
        LOGGER.info("Persistencia producto correcta.");
    }

    @Override
    public void validateRequestNewTransaction(ProductoWhitParentRequest productoRequest) throws IllegalArgumentException {
        Assert.notNull(productoRequest.getAmount(), "Campo amount es requerido.");
        Assert.notNull(productoRequest.getType(), "Campo type es requerido.");
        Assert.isTrue( !productoRequest.getType().isBlank(), "Campo type es requerido.");
        Assert.isTrue( !productoRequest.getTransaccionId().equals(productoRequest.getParentId()) , "El transaccionId y el parentId deben ser diferentes.");
        Assert.isTrue( !productoRepository.findById(productoRequest.getTransaccionId()).isPresent(), "El transaccionId informado ya existe.");
        Assert.isTrue(productoRepository.findById(productoRequest.getParentId()).isPresent(), "El parentId informado no existe.");
        LOGGER.info("Validaciones de ProductoRequest correstas.");
    }

    @Override
    public void createTransaction(ProductoWhitParentRequest productoRequest) {
        Producto producto = new Producto(productoRequest.getTransaccionId(), productoRequest.getAmount(), 
                productoRequest.getType(), productoRequest.getParentId());
        productoRepository.save(producto);
        LOGGER.info("Persistencia producto correcta.");
    }

    @Override
    public List<Long> getTransactionIds(String type) {
        List<Producto> productos = productoRepository.findByType(type);
        List<Long> transactionsIds = new ArrayList<Long>();
        for (Producto producto : productos) {
            transactionsIds.add(producto.getTransaccionId());
        }
        return transactionsIds;
    }

    @Override
    public SumDto getSumTransactions(Long transactionsId) {
        List<Producto> productos = productoRepository.findByParentId(transactionsId);
        SumDto sumDto = new SumDto();
        for (Producto producto : productos) {
            sumDto.sum(producto.getAmount());
        }
        return sumDto;
    }
    
}
