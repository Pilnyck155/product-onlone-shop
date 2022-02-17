package service;

import dao.ProductDao;
import entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    ProductDao productDao = mock(ProductDao.class);
    ProductService productService = new ProductService(productDao);

    @Test
    @DisplayName("test findAll products passed successful")
    public void whenCallMethod_thenObtainAllProducts(){
        Product firstProduct = new Product("milk", 25, new Date(2021-4-17));
        Product secondProduct = new Product("bread", 22, new Date(2021-6-21));
        Product thirdProduct = new Product("honey", 18, new Date(2021-2-30));
        List<Product> expectedList = List.of(firstProduct, secondProduct,thirdProduct);

        when(productDao.findAll()).thenReturn(expectedList);
        List<Product> actualList = productService.findAll();
        assertEquals(3, actualList.size());
        assertEquals("milk", actualList.get(0).getName());
        assertEquals("bread", actualList.get(1).getName());
        assertEquals("honey", actualList.get(2).getName());
    }

    @Test
    @DisplayName("test save product passed successful")
    public void whenCallMethod_thenCheckIfMethodWasCall(){
        Product firstProduct = new Product("milk", 25, new Date(2021-4-17));

        doNothing().when(productDao).save(firstProduct);
        productService.saveProduct(firstProduct);
        verify(productDao).save(firstProduct);
    }
}