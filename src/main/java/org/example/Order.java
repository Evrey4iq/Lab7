package org.example;
import java.util.Map;
public class Order {

    private Integer id;
    private Integer userId;
    private Map<Product, Integer> orderDetails;
    private double totalPrice;

    public Order() {
    }

    public Order(Integer id, Integer userId, Map<Product, Integer> orderDetails, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderDetails = orderDetails;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Map<Product, Integer> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Map<Product, Integer> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderDetails=" + orderDetails +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public void calculateTotalPrice() {
        this.totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : this.orderDetails.entrySet()) {
            this.totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
    }

    public void validate() {
        if (this.userId == null) {
            throw new RuntimeException("UserId must be not null");
        }

        if (this.orderDetails == null || this.orderDetails.isEmpty()) {
            throw new RuntimeException("OrderDetails must not be null or empty");
        }

        for (Map.Entry<Product, Integer> entry : this.orderDetails.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product == null) {
                throw new RuntimeException("Product must not be null");
            }

            if (quantity <= 0) {
                throw new RuntimeException("Quantity must be greater than or equal to 0");
            }

            if (product.getStock() < quantity) {
                throw new RuntimeException("Not enough stock for product " + product.getName());
            }
        }
    }
}