package service;

import dao.jdbc.JdbcProductDao;
import entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    JdbcProductDao productDao = mock(JdbcProductDao.class);
    ProductService productService = new ProductService(productDao);

    @Test
    @DisplayName("test findAll products passed successful")
    public void whenCallMethod_thenObtainAllProducts() {
        Product firstProduct = new Product("milk", 25, new Date(2021 - 4 - 17));
        Product secondProduct = new Product("bread", 22, new Date(2021 - 6 - 21));
        Product thirdProduct = new Product("honey", 18, new Date(2021 - 2 - 30));
        List<Product> expectedList = List.of(firstProduct, secondProduct, thirdProduct);

        when(productDao.findAll()).thenReturn(expectedList);
        List<Product> actualList = productService.findAll();
        assertEquals(3, actualList.size());
        assertEquals("milk", actualList.get(0).getName());
        assertEquals("bread", actualList.get(1).getName());
        assertEquals("honey", actualList.get(2).getName());
    }

    @Test
    @DisplayName("test save product passed successful")
    public void whenCallSaveMethod_thenCheckIfMethodWasCall() {
        Product firstProduct = new Product("milk", 25, new Date(2021 - 4 - 17));

        doNothing().when(productDao).save(firstProduct);
        productService.saveProduct(firstProduct);
        verify(productDao).save(firstProduct);
    }

    @Test
    @DisplayName("test delete product by id method passed successful")
    public void whenCallDeleteMethod_andIdIsValid_thenDeleteProductById() {
        int id = 5;

        doNothing().when(productDao).deleteById(id);
        productService.deleteById(id);
        verify(productDao).deleteById(id);
    }

    @Test
    @DisplayName("test edit product by id method passed successful")
    public void whenCallEditMethod_andIdIsValid_thenEditProductById() {
        Product firstProduct = new Product("milk", 25, new Date(2021 - 4 - 17));

        doNothing().when(productDao).editProductById(firstProduct);
        productService.editProductById(firstProduct);
        verify(productDao).editProductById(firstProduct);
    }

    @Test
    @DisplayName("test get product by id method passed successful")
    public void whenCIdIsValid_thenObtainProductById() {
        Product expectedProduct = new Product(5,"milk", 25, new Date(2021 - 4 - 17));

        when(productDao.getProductById(5)).thenReturn(expectedProduct);

        Product actualProduct = productService.getProductById(5);
        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getId(), actualProduct.getId());
        assertEquals(expectedProduct.getName(), actualProduct.getName());
        assertEquals(expectedProduct.getCreationDate(), actualProduct.getCreationDate());

        verify(productDao).getProductById(5);
    }
}