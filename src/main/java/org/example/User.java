package org.example;
import java.util.HashMap;
import java.util.Map;
public class User {

    private Integer id;
    private String username;
    private Map<Product, Integer> cart;

    public User() {
        this.cart = new HashMap<>();
    }

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
        this.cart = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    public void addProductToCart(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than or equal to 0");
        }

        if (!this.cart.containsKey(product)) {
            this.cart.put(product, quantity);
        } else {
            int currentQuantity = this.cart.get(product);

            if (product.getStock() < currentQuantity + quantity) {
                throw new RuntimeException("Not enough stock for product " + product.getName());
            }

            this.cart.put(product, currentQuantity + quantity);
        }
    }

    public void removeProductFromCart(Product product) {
        if (!this.cart.containsKey(product)) {
            return;
        }

        this.cart.remove(product);
    }

    public void updateProductQuantityInCart(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than or equal to 0");
        }

        if (!this.cart.containsKey(product)) {
            throw new RuntimeException("Product " + product.getName() + " is not in the cart");
        }

        int currentQuantity = this.cart.get(product);

        if (quantity > currentQuantity) {
            throw new RuntimeException("Not enough quantity for product " + product.getName());
        }

        this.cart.put(product, quantity);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", cart=" + cart +
                '}';
    }
}