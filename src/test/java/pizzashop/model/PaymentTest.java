package pizzashop.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    @Disabled
    void getTableNumber() {
        Payment payment = new Payment(5, PaymentType.CARD, 200.0);

        int tableNumber = payment.getTableNumber();

        assert tableNumber == 5;
    }

}