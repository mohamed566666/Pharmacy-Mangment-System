import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        Product ProductToADD = new Product("Vitamin D", "vitamin d", 25.50, 20);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("Oblex", "oblex", 35.00, 15);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("Tusskan", "tusskan", 55.00, 5);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("Concor", "concor", 55.00, 5);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("Aspirin", "aspirin", 12.50, 50);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("Antibiotic", "antibiotic", 25.75, 30);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("Vitamin C", "vitamin v", 8.00, 100);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("Thorazine", "thorazine", 30.00, 15);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("Congestal", "congestal", 40.00, 50);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("123", "123", 15.00, 30);
        inventory.addProduct(ProductToADD);
        ProductToADD = new Product("Ansoline", "ansoline", 60.00, 30);
        inventory.addProduct(ProductToADD);
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
        int backTOMenu = 0;
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
                                productId = productId.toLowerCase();
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
                    backTOMenu = 0;
                    while (backTOMenu != 1) {
                        System.out.println("Press 1 to Back to Menu");
                        backTOMenu = scanner.nextInt();
                    }
                    break;
                case 2:
                    System.out.println("Inventory:");
                    inventory.display();
                    backTOMenu = 0;
                    while (backTOMenu != 1) {
                        System.out.println("Press 1 to Back to Menu");
                        backTOMenu = scanner.nextInt();
                    }
                    break;
                case 3:
                    System.out.println("Update Inventory:");
                    System.out.print("Enter product ID to update: ");
                    String productIdToUpdate = scanner.nextLine();
                    productIdToUpdate = productIdToUpdate.toLowerCase();
                    System.out.print("Enter quantity to update (positive for increase, negative for decrease): ");
                    int quantityUpdate = scanner.nextInt();
                    scanner.nextLine();
                    doctor.updateProductQuantity(productIdToUpdate, quantityUpdate, quantityUpdate > 0);
                    backTOMenu = 0;
                    while (backTOMenu != 1) {
                        System.out.println("Press 1 to Back to Menu");
                        backTOMenu = scanner.nextInt();
                    }
                    break;
                case 4:
                    System.out.println("Add Product to Inventory:");
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product ID: ");
                    String productIdToAdd = scanner.nextLine();
                    productIdToAdd = productIdToAdd.toLowerCase();
                    System.out.print("Enter price per unit: ");
                    double productPrice = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int productQuantity = scanner.nextInt();
                    if (productQuantity < 0) {
                        System.out.println("Quantity can't be Negative");
                        break;
                    }
                    scanner.nextLine();
                    Product newProduct = new Product(productName, productIdToAdd, productPrice, productQuantity);
                    doctor.addProductToInventory(newProduct);
                    backTOMenu = 0;
                    while (backTOMenu != 1) {
                        System.out.println("Press 1 to Back to Menu");
                        backTOMenu = scanner.nextInt();
                    }
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
