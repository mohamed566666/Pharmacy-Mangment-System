import java.util.ArrayList;
public class Order {
    private Doctor doctor;
    private Customer customer;
    private ArrayList<Product> orderedProducts;
    private boolean status;
    private ManageCustomerDiscount discountManager;

    public Order(Doctor doctor, Customer customer, ManageCustomerDiscount discountManager) {
        this.doctor = doctor;
        this.customer = customer;
        this.orderedProducts = new ArrayList<>();
        this.status = false;
        this.discountManager = discountManager;
    }

    public Customer getcustomer() {
        return customer;
    }

    // Add a product with the specified quantity to the order
    public void addProductToOrder(Product product, int quantity) {
        orderedProducts.add(new Product(product.getName(), product.getProductId(), product.getPrice(), quantity));
        System.out.println(quantity + " units of " + product.getName() + " added to the order.");
    }

    // Remove a product from the order by productId
    public void removeProductFromOrder(String productId) {
        for (int i = 0; i < orderedProducts.size(); i++) {
            if (orderedProducts.get(i).getProductId() == productId) {
                System.out.println(orderedProducts.get(i).getName() + " removed from the order.");
                orderedProducts.remove(i);
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found in the order.");
    }

    // Get all ordered products
    public ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }

    // Calculate the total price of the order
    public double calculateTotalPrice() {
        double total = 0;
        for (Product product : orderedProducts) {
            total += product.getPrice() * product.getQuantity();
        }
        double discount = discountManager.calculateDiscount(customer.getcustomerId());    // for check 
        total -= total * discount;
        return total;
    }   

    // Finalize the order and set the status to complete
    public void finalizeOrder() {
        status = true;
        discountManager.checkAndUpdate(customer.getcustomerId());
        System.out.println("Order has been finalized.");
    }

    // Cancel the order and clear the ordered products
    public void cancelOrder() {
        orderedProducts.clear();
        status = false;
        System.out.println("Order has been canceled.");
    }

    // Check if the order is complete
    public boolean isOrderComplete() {
        return status;
    }

    // Display the order details
    public void displayOrderDetails() {
        System.out.println("Order Details:");
        System.out.println(" customer :");
        customer.display();
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Status: " + (status ? "Completed" : "Incomplete"));
        System.out.println("Products ordered:");
        for (Product product : orderedProducts) {
            System.out.println(" - " + product.getName() + ": " + product.getQuantity() + " units, Price per unit: $"
                    + product.getPrice());
        }
        System.out.println("Total Price: " + calculateTotalPrice());
    }
}
