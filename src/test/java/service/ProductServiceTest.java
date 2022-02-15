package service;

import entity.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    public void testFindAll(){
        ProductService productService = new ProductService();
        List<Product> productList = productService.findAll();
        assertEquals(3, productList.size());

        assertEquals("milk", productList.get(0).getName());
        assertEquals("bread", productList.get(1).getName());
        assertEquals("honey", productList.get(2).getName());
    }
}