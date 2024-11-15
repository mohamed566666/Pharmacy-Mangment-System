public class Customer {
    private String name;
    private String customerId;
    private String phone;

    // constructor
    public Customer(String name, String customerId, String phone) {
        this.name = name;
        this.customerId = customerId;
        this.phone = phone;
    }

    // method to get the name of this customer
    public String getname() {
        return name;
    }

    // method to get ID of this customer
    public String getcustomerId() {
        return customerId;
    }

    // method to get Phone Number of this customer
    public String getphone() {
        return phone;
    }

    // method to display All details of this customer
    public void display() {
        System.out.println("name : " + getname() + "\n" + "phone : " + getphone() + "\n" + "customerId : " + getcustomerId());
    }

}
