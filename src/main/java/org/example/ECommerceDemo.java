package org.example;

import java.sql.SQLException;
import java.util.*;

public class ECommerceDemo {

    public static void main(String[] args) throws SQLException {
        ECommercePlatform platform = new ECommercePlatform();

        User user1 = new User(1, "Martin Anderson");
        User user2 = new User(2, "Anna Green");
        platform.addUser(user1);
        platform.addUser(user2);

        Product product1 = new Product(1, "Samsung S23", 1500.0, 100);
        Product product2 = new Product(2, "Samsung UE43", 2000.0, 50);
        platform.addProduct(product1);
        platform.addProduct(product2);


        user1.addProductToCart(product1, 1);

        user2.addProductToCart(product2, 2);

        platform.createOrder(1, user1.getId(), user1.getCart(), 1000.0);

        platform.createOrder(2, user2.getId(), user2.getCart(), 4000.0);

        platform.processOrders();

        platform.recommendProducts(user1);
        platform.recommendProducts(user2);

        System.out.println("End user status:");
        for (User user : platform.getUsers()) {
            System.out.println(user.toString());
        }

        System.out.println("End user status:");
        for (Product product : platform.getProducts(null)) {
            System.out.println(product.toString());
        }

        System.out.println("End user status:");
        for (Order order : platform.getOrders()) {
            System.out.println(order.toString());
        }
    }
}