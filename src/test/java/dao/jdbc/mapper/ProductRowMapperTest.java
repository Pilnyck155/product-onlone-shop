package dao.jdbc.mapper;

import entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRowMapperTest {

    @Test
    void mapRow() throws SQLException {
        ProductRowMapper productRowMapper = new ProductRowMapper();
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getInt("id")).thenReturn(Integer.valueOf("10"));
        when(mockResultSet.getString("name")).thenReturn("milk");
        when(mockResultSet.getInt("price")).thenReturn(17);
        when(mockResultSet.getString("creation_date")).thenReturn("2021-11-18");

        Product actualProduct = productRowMapper.mapRow(mockResultSet);

        assertEquals(10, actualProduct.getId());
        assertEquals("milk", actualProduct.getName());
        assertEquals(17, actualProduct.getPrice());
        assertEquals(LocalDate.parse("2021-11-18"), actualProduct.getCreationDate());
    }
}