package service;

import dao.ProductDao;
import entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    ProductDao productDao = mock(ProductDao.class);
    ProductService productService = new ProductService(productDao);

    @Test
    @DisplayName("test findAll products passed successful")
    public void whenCallFindAll_thenObtainAllProducts() {
        Product firstProduct = new Product("milk", 25, LocalDate.of(2021, 4, 17));
        Product secondProduct = new Product("bread", 22, LocalDate.of(2021, 6, 21));
        Product thirdProduct = new Product("honey", 18, LocalDate.of(2021, 3, 30));
        List<Product> expectedList = List.of(firstProduct, secondProduct, thirdProduct);

        when(productDao.findAll()).thenReturn(expectedList);

        List<Product> actualList = productService.findAll();
        assertEquals(3, actualList.size());
        assertEquals(firstProduct, actualList.get(0));
        assertEquals(secondProduct, actualList.get(1));
        assertEquals(thirdProduct, actualList.get(2));
        verify(productDao).findAll();
    }

    @Test
    @DisplayName("test save product passed successful")
    public void whenCallSaveMethod_thenCheckIfMethodWasCall() {
        Product firstProduct = new Product("milk", 25, LocalDate.of(2021, 4, 17));

        productService.saveProduct(firstProduct);
        verify(productDao).save(firstProduct);
    }

    @Test
    @DisplayName("test delete product by id method passed successful")
    public void whenCallDeleteMethod_andIdIsValid_thenDeleteProductById() {
        int id = 5;

        productService.deleteById(id);
        verify(productDao).deleteById(id);
    }

    @Test
    @DisplayName("test edit product by id method passed successful")
    public void whenCallEditMethod_andIdIsValid_thenEditProductById() {
        Product firstProduct = new Product("milk", 25, LocalDate.of(2021, 4, 17));

        productService.editProductById(firstProduct);
        verify(productDao).editProductById(firstProduct);
    }

    @Test
    @DisplayName("test get product by id method passed successful")
    public void whenIdIsValid_thenObtainProductById() {
        Product expectedProduct = new Product(5,"milk", 25, LocalDate.of(2021, 4, 17));

        when(productDao.getProductById(5)).thenReturn(expectedProduct);

        Product actualProduct = productService.getProductById(5);
        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getId(), actualProduct.getId());
        assertEquals(expectedProduct.getName(), actualProduct.getName());
        assertEquals(expectedProduct.getCreationDate(), actualProduct.getCreationDate());

        verify(productDao).getProductById(5);
    }
}