package com.example;

import com.example.model.request.ProductoRequest;
import com.example.model.request.ProductoWhitParentRequest;
import com.example.repositorys.ProductoRepository;
import com.example.services.ProductoService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class BasicApplicationTests {

    private String typeAuto = "auto";
    private Long parentId = 10L;

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoService productoService;

    // Validaciones
    @Test
    @Order(1)
    public void testProductoRequest() {
        ProductoRequest request = new ProductoRequest(10L, 20.5, "moto");
        try {
            productoService.validateRequestNewTransaction(request);
        } catch (Exception e) {
            Assert.isTrue(false, e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void testProductoWhitParentRequest() {
        ProductoWhitParentRequest request = new ProductoWhitParentRequest(11L, 39.5, typeAuto, parentId);
        try {
            productoService.validateRequestNewTransaction(request);
        } catch (Exception e) {
            Assert.isTrue(false, e.getMessage());
        }
    }

    // Servicios
    @Test
    @Order(2)
    public void testCreateProductoRequest() {
        ProductoRequest request = new ProductoRequest(10L, 20.5, "moto");
        try {
            productoService.createTransaction(request);
        } catch (Exception e) {
            Assert.isTrue(false, e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void testCreateProductoWhitParentRequest() {
        ProductoWhitParentRequest request = new ProductoWhitParentRequest(11L, 39.5, typeAuto, parentId);
        try {
            productoService.createTransaction(request);
        } catch (Exception e) {
            Assert.isTrue(false, e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void testGetByType() {
        ProductoWhitParentRequest request2 = new ProductoWhitParentRequest(12L, 39.5, typeAuto, parentId);
        ProductoWhitParentRequest request3 = new ProductoWhitParentRequest(13L, 39.5, typeAuto, parentId);
        ProductoWhitParentRequest request = new ProductoWhitParentRequest(14L, 39.5, typeAuto, parentId);
        try {
            productoService.createTransaction(request);
            productoService.createTransaction(request2);
            productoService.createTransaction(request3);
            Assertions.assertEquals(productoService.getTransactionIds(typeAuto).size(), 4);
        } catch (Exception e) {
            Assert.isTrue(false, e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void testGetSum() {
        try {
            Assertions.assertEquals(productoService.getSumTransactions(parentId).getSum(), 158.0);
        } catch (Exception e) {
            Assert.isTrue(false, e.getMessage());
        }
    }

    @AfterAll
    public void cleanData() {
        try {
            productoRepository.deleteAll();
        } catch (Exception e) {
            Assert.isTrue(false, e.getMessage());
        }
    }

}
