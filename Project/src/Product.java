public class Product {
  private String name;
  private String productId;
  private double price;
  private int quantity;

  public Product(String name, String productId, double price, int quantity) {
    this.name = name;
    this.productId = productId;
    this.price = price;
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public String getProductId() {
    return productId;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void increasequantity(int amount) // exception
  {
    if (amount > 0)
      quantity += amount;
  }

  public void decreasequantity(int amount) // exception
  {
    if (quantity >= amount)
      quantity -= amount;
  }

  public void display() {
    System.out.println("Product ID: " + getProductId() +
        ", Name: " + getName() +
        ", Quantity: " + getQuantity());
  }
}