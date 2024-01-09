package org.example;
import java.util.Comparator;
public class ProductComparators {

    public static class ProductNameComparator implements Comparator<Product> {

        @Override
        public int compare(Product o1, Product o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static class ProductStockComparator implements Comparator<Product> {

        @Override
        public int compare(Product o1, Product o2) {
            return Integer.compare(o1.getStock(), o2.getStock());
        }
    }
}
