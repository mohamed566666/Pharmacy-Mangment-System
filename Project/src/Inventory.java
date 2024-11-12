import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Inventory {
    private HashMap<String, Product> products;

    public Inventory() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void removeproduct(Product product) {
        if (products.get(product.getProductId())!=null)
            products.remove(product.getProductId());
    }

    public HashMap<String, Product> getAllProducts() {
        return products;
    }

    public void updateQuantity(Product product, int quantity, boolean add) {
        if (add) {
            product.increasequantity(quantity);
        } else if (product.getQuantity() >= quantity) {
            product.decreasequantity(quantity);
        } else {
            System.out.println("Insufficient quantity to reduce.");
        }
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public boolean isProductAvailable(String productId, int quantity) {
        Product product = products.get(productId);
        return (product != null) && (product.getQuantity() >= quantity);
    }

    public void writeInventoryToFile(String fileName) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true); // 'true' to append to the file
            for (Product product : products.values()) {
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
                    writer.close(); // Close the FileWriter to release resources
                }
            } catch (IOException e) {
                System.out.println("An error occurred while closing the file.");
                e.printStackTrace();
            }
        }
    }

    public void display() {

        for (Product product : products.values()) {
            product.display();
        }
    }

}