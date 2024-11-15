import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        Product product1 = new Product("Aspirin", "P001", 12.50, 50);
        Product product2 = new Product("Antibiotic", "P002", 25.75, 30);
        Product product3 = new Product("Vitamin C", "P003", 8.00, 100);
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);
        Customer customer = null;
        Order order = null;
        ManageCustomerDiscount discountManager = new ManageCustomerDiscount();
        ArrayList<Order> doctorOrders = new ArrayList<>();
        Doctor doctor = new Doctor("Dr. Smith", "D001", doctorOrders, inventory);
        System.out.println("Doctor login:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        Login doctorLogin = new Login(username, password);
        if (!doctorLogin.login()) {
            System.out.println("Login failed. Exiting program.");
            scanner.close();
            return;
        }
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create new order");
            System.out.println("2. View inventory");
            System.out.println("3. Update inventory");
            System.out.println("4. Add product to inventory");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter customer ID: ");
                    String customerId = scanner.nextLine();
                    customer = new Customer("Customer " + customerId, customerId, "000-000-0000");
                    order = doctor.createNewOrder(customer, discountManager);
                    boolean orderComplete = false;
                    while (!orderComplete) {
                        System.out.println("\nOrder Management:");
                        System.out.println("1. Add product to order");
                        System.out.println("2. View order details");
                        System.out.println("3. Finalize order");
                        System.out.println("4. Cancel order");
                        System.out.print("Enter your choice: ");
                        int orderChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (orderChoice) {
                            case 1:
                                System.out.print("Enter product ID to add to order: ");
                                String productId = scanner.nextLine();
                                System.out.print("Enter quantity: ");
                                int quantity = scanner.nextInt();
                                scanner.nextLine();
                                doctor.addProductToOrder(order, productId, quantity);
                                break;
                            case 2:
                                order.displayOrderDetails();
                                break;
                            case 3:
                                doctor.finalizeOrder(order);
                                orderComplete = true;
                                break;
                            case 4:
                                doctor.cancelOrder(order);
                                orderComplete = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Inventory:");
                    inventory.display();
                    break;
                case 3:
                    System.out.println("Update Inventory:");
                    System.out.print("Enter product ID to update: ");
                    String productIdToUpdate = scanner.nextLine();
                    System.out.print("Enter quantity to update (positive for increase, negative for decrease): ");
                    int quantityUpdate = scanner.nextInt();
                    scanner.nextLine();
                    doctor.updateProductQuantity(productIdToUpdate, quantityUpdate, quantityUpdate > 0);
                    break;
                case 4:
                    System.out.println("Add Product to Inventory:");
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product ID: ");
                    String productIdToAdd = scanner.nextLine();
                    System.out.print("Enter price per unit: ");
                    double productPrice = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int productQuantity = scanner.nextInt();
                    scanner.nextLine();
                    Product newProduct = new Product(productName, productIdToAdd, productPrice, productQuantity);
                    doctor.addProductToInventory(newProduct);
                    break;
                case 5:
                    doctorLogin.logout();
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
