import java.util.HashMap;

public class ManageCustomerDiscount {
    private HashMap<String, Integer> frequencyForCustomer;

    public ManageCustomerDiscount() {
        this.frequencyForCustomer = new HashMap<>();
    }

    public void addCustomer(String id) {
        frequencyForCustomer.put(id, frequencyForCustomer.get(id) + 1);
    }

    public void checkAndUpdate(String id) {
        if (!frequencyForCustomer.containsKey(id)) {
            addCustomer(id);
        } else {
            frequencyForCustomer.put(id, frequencyForCustomer.get(id) + 1);
        }
    }

    public double calculateDiscount(String id) {
        if (!frequencyForCustomer.containsKey(id)) {
            return 0;
        }

        int purchaseFrequency = frequencyForCustomer.get(id);
        double discount;

        if (purchaseFrequency >= 20) {
            discount = 0.20;
        } else if (purchaseFrequency >= 10) {
            discount = 0.10;
        } else if (purchaseFrequency >= 5) {
            discount = 0.05;
        } else {
            discount = 0.0;
        }

        return discount;
    }
}
