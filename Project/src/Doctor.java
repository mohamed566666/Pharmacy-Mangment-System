import java.util.ArrayList;

public class Doctor {
    private String name;
    private String doctorId;
    private ArrayList<Order> currentOrders;
    private Inventory inventory;

    // constructor
    public Doctor(String name, String doctorId, ArrayList<Order> currentOrders, Inventory inventory) {
        this.name = name;
        this.doctorId = doctorId;
        this.currentOrders = currentOrders;
        this.inventory = inventory;
    }

    // method to get Doctor's Name
    public String getName() {
        return name;
    }

    // method to get Doctor's ID
    public String getDoctorId() {
        return doctorId;
    }

    // Method to Create a new order for a customer
    public Order createNewOrder(Customer customer, ManageCustomerDiscount dis) {
        Order newOrder = new Order(this, customer, dis);
        currentOrders.add(newOrder);
        System.out.println("Created a new order for " + customer.getname());
        return newOrder;
    }

    // Method to Add a product to an order by checking the inventory first
    public void addProductToOrder(Order order, String productId, int quantity) {
        if (inventory == null) {
            System.out.println("Inventory is not initialized.");
            return;
        }
        Product product = inventory.getProduct(productId);

        if (product == null) {
            System.out.println("Product not found in inventory.");
            return;
        }

        if (inventory.isProductAvailable(productId, quantity)) {
            order.addProductToOrder(product, quantity);
            inventory.updateQuantity(product, quantity, false);
            System.out.println("Added " + quantity + " units of " + product.getName() + " to the order.");
        } else {
            System.out.println("Insufficient stock for product: " + product.getName());
            System.out.println("failled to Add this product " + product.getName());
        }
    }

    // Method Finalize an order
    public void finalizeOrder(Order order) {
        if (order.getOrderedProducts().isEmpty()) {
            System.out.println("No products in the order to finalize.");
        } else {
            order.finalizeOrder();
        }
    }

    // Method Cancel an order
    public void cancelOrder(Order order) {
        if (currentOrders.contains(order)) {
            order.cancelOrder(inventory);
            currentOrders.remove(order); // Remove the order from the list
        } else {
            System.out.println("Order not found in current orders.");
        }
    }

    // Method View inventory products
    public void displayOrders() {
        System.out.println("\nNumber of Complated Orders : "+currentOrders.size());
        for (var order : currentOrders) {
            order.displayOrderDetailsforHistory();
        }
    }

    // Method to Add a new product to the inventory
    public void addProductToInventory(Product product) {
        if (inventory == null) {
            System.out.println("Inventory is not initialized.");
            return;
        }
        inventory.addProduct(product);
        System.out.println("Added product " + product.getName() + " to the inventory.");
    }

    // Update quantity of an existing product in the inventory
    public void updateProductQuantity(String productId, int quantity, boolean add) {
        if (inventory == null) {
            System.out.println("Inventory is not initialized.");
            return;
        }
        Product product = inventory.getProduct(productId);
        if (product != null) {
            inventory.updateQuantity(product, quantity, add);
            String actionStr = (add) ? "Increased" : "Decreased";
            System.out.println(actionStr + " quantity of " + product.getName() + " by " + quantity + ".");
        } else {
            System.out.println("Product not found in inventory.");
        }
    }
}
