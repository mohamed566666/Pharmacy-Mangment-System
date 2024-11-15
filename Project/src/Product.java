public class Product {
  private String name;
  private String productId;
  private double price;
  private int quantity;

  // constructor
  public Product(String name, String productId, double price, int quantity) {
    this.name = name;
    this.productId = productId;
    this.price = price;
    this.quantity = quantity;
  }

  // method to get the name of product
  public String getName() {
    return name;
  }

  // method to get the ID of product
  public String getProductId() {
    return productId;
  }

  // method to get the Price of product
  public double getPrice() {
    return price;
  }

  // method to get the Available Quantity of product
  public int getQuantity() {
    return quantity;
  }

  // method to increase Quantity of The product
  public void increasequantity(int amount) {
    if (amount > 0) // check that amount is positive
      quantity += amount;
  }

  // method to decrease Quantity of The product
  public void decreasequantity(int amount) {
    if (quantity >= amount) // check that amount is smaller than Available Quantity to Avoid get NegativeQuantity
      quantity -= amount;
  }

  // method to display the details of this product
  public void display() {
    System.out.println("Product ID: " + getProductId() + ", Name: " + getName() + ", Quantity: " + getQuantity());
  }
}
