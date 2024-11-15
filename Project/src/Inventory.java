import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Inventory {
    private HashMap<String, Product> products;

    // constructor
    public Inventory() {
        products = new HashMap<>();
    }

    // method to add a specific product to (products)hashmap
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    // method to remove a specific product from (products)hashmap
    public void removeproduct(Product product) {
        if (products.get(product.getProductId()) != null)
            products.remove(product.getProductId());
    }

    // method to return all products Available in inventory
    public HashMap<String, Product> getAllProducts() {
        return products;
    }

    // method to update Quantity of a specific product
    public void updateQuantity(Product product, int quantity, boolean add) {
        if (add) {
            product.increasequantity(quantity);
        } else if (product.getQuantity() >= quantity) {
            product.decreasequantity(quantity);
        } else {
            System.out.println("Insufficient quantity to reduce.");
        }
    }

    // method to return a specific product by its productID
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    // method to return boolean flag to answer query if product is available or not
    public boolean isProductAvailable(String productId, int quantity) {
        Product product = products.get(productId);
        return (product != null) && (product.getQuantity() >= quantity);
    }

    public void writeInventoryToFile(String fileName) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true);
            for (var product : products.values()) {
                writer.write("Product ID: " + product.getProductId() +
                        ", Name: " + product.getName() +
                        ", Quantity: " + product.getQuantity() + "\n");
            }
            System.out.println("Inventory has been written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the inventory to the file.");
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("An error occurred while closing the file.");
                e.printStackTrace();
            }
        }
    }

    // method to desplay all products available in this Inventory
    public void display() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
        } else {
            for (var product : products.values()) {
                product.display();
            }
        }
    }
}
