package web;

import entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;


public class ProductManager {
    public static Product getProductFromRequest(HttpServletRequest request) {
        String stringId = request.getParameter("id");
        int id = 0;
        if (stringId != null) {
            id = Integer.parseInt(stringId);
        }
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        LocalDate creationDate = LocalDate.parse(request.getParameter("creationDate"));
        return Product.builder()
                .id(id)
                .name(productName)
                .price(price)
                .creationDate(creationDate)
                .build();
    }
}
