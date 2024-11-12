public class Customer {
    private String name;
    private String  customerId;
    private String phone;

    public Customer(String name, String customerId, double discount, String phone) {
        this.name = name;
        this.customerId = customerId;
        this.phone = phone;
    }

    public String getname() {
        return name;
    }

    public String getcustomerId() {
        return customerId;
    }

    public String getphone() {
        return phone;
    }

    public void display() {
        System.out.println(
                "name : " + getname() + "\n" + "phone : " + getphone() + "\n" + "customerId : " + getcustomerId());
    }

}