package CoreFeatures.AOP;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {

    public void checkout(String status) {
        //Logging
        //Authentication & Authorization
        //Sanitize the Data
        System.out.println("Checkout Method from ShoppingCart called.");
    }

    public int quantity(){
        System.out.println("Quantity method from ShoppingCart called");
        return 2;
    }
}
