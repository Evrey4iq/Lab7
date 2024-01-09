package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ECommercePlatform {

    private static final int PRODUCTS_CACHE_SIZE = 100;
    private static final int USERS_CACHE_SIZE = 1000;
    private static final int ORDERS_CACHE_SIZE = 10000;

    private ConcurrentHashMap<Integer, Product> products;
    private ConcurrentHashMap<Integer, User> users;
    private ConcurrentHashMap<Integer, Order> orders;

    public ECommercePlatform() {
        this.products = new ConcurrentHashMap<>();
        this.users = new ConcurrentHashMap<>();
        this.orders = new ConcurrentHashMap<>();
    }

    public void addProduct(Product product) {
        this.products.put(product.getId(), product);
    }

    public void addUser(User user) {
        this.users.put(user.getId(), user);
    }

    public void createOrder(int orderId, int userId, Map<Product, Integer> cart, double totalPrice) {
        Order order = new Order(orderId, userId, cart, totalPrice);
        order.validate();
        List<Product> reservedProducts = reserveProducts(order);
        if (reservedProducts.isEmpty()) {
            throw new RuntimeException("Not enough stock for order");
        }
        this.orders.put(orderId, order);
    }


    public List<Product> getAvailableProducts() {
        List<Product> products = getProductsFromCache(PRODUCTS_CACHE_SIZE);

        if (products == null) {
            products = getAvailableProductsFromDatabase();
            putProductsToCache(products);
        }

        return products;
    }

    private List<Product> getProductsFromCache(int cacheSize) {
        return this.products.values().stream()
                .filter(product -> product.getStock() > 0)
                .limit(cacheSize)
                .collect(Collectors.toList());
    }

    private List<Product> getAvailableProductsFromDatabase() {
        List<Product> products = new ArrayList<>();



        return products;
    }

    private void putProductsToCache(List<Product> products) {
        this.products.putAll(products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product)));
    }

    public List<User> getUsers() {
        return getUsersFromCache(USERS_CACHE_SIZE);
    }

    private List<User> getUsersFromCache(int cacheSize) {
        return new ArrayList<>(this.users.values());
    }

    public List<Order> getOrders() {
        return getOrdersFromCache(ORDERS_CACHE_SIZE);
    }

    private List<Order> getOrdersFromCache(int cacheSize) {
        return new ArrayList<>(this.orders.values());
    }

    public void updateProductStock(int productId, int quantity) {
        Product product = this.products.get(productId);

        if (product != null) {
            product.setStock(product.getStock() + quantity);
        }
    }

    private List<Product> reserveProducts(Order order) {
        List<Product> reservedProducts = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : order.getOrderDetails().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.getStock() >= quantity) {
                product.setStock(product.getStock() - quantity);
                reservedProducts.add(product);
            }
        }

        return reservedProducts;
    }

    public List<Product> getProducts(Comparator<Product> comparator) {
        List<Product> products = new ArrayList<>(this.products.values());


        if (comparator != null) {
            products.sort(comparator);
        }

        return products;
    }

    public void processOrders() {

    }

    public void recommendProducts(User user) {

        System.out.println("Рекомендовані товари для користувача " + user.getUsername() + ":");
        List<Product> recommendedProducts = new ArrayList<>(); // Заглушка, замените на реальную логику
        for (Product product : recommendedProducts) {
            System.out.println(product);
        }
    }
}