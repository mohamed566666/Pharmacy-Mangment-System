import java.util.ArrayList;

@SuppressWarnings("unused")
public class Doctor {
    private String name;
    private String doctorId;
    private String userName;
    private String password;

    private ArrayList<Order> currentOrders;
    private Inventory inventory;

    public Doctor(String name, String doctorId, String userName, String password, ArrayList<Order> currentOrders,
            Inventory inventory) {
        this.name = name;
        this.doctorId = doctorId;
        this.userName = userName;
        this.password = password;
        this.currentOrders = currentOrders;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public String getDoctorId() {
        return doctorId;
    }

    // Create a new order for a customer
    public Order createNewOrder(Customer customer , ManageCustomerDiscount dis) {
        Order newOrder = new Order(this, customer,dis);
        currentOrders.add(newOrder);
        System.out.println("Created a new order for " + customer.getname());
        return newOrder;
    }

    // Add a product to an order by checking the inventory first
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
        }
    }

    // Finalize an order
    public void finalizeOrder(Order order) {
        if (order.getOrderedProducts().isEmpty()) {
            System.out.println("No products in the order to finalize.");
        } else {
            order.finalizeOrder();
            System.out.println("Order has been finalized.");
        }
    }

    // Cancel an order
    public void cancelOrder(Order order) {
        currentOrders.remove(order);
        order.cancelOrder();
        System.out.println("Order has been canceled.");
    }

    // View inventory products
    public void displayOrders() {
        for (var order : currentOrders) {
            order.displayOrderDetails();
        }
    }

    // Add a new product to the inventory
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
