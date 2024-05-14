package pizzashop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Lab04PaymentTest {

    @Test
    void getTableNumber() {
        Payment payment = new Payment(5, PaymentType.CARD, 200.0);

        int tableNumber = payment.getTableNumber();

        assertEquals(5, tableNumber);
    }

    @Test
    void setTableNumber() {
        Payment payment = new Payment(5, PaymentType.CARD, 200.0);

        payment.setTableNumber(1);

        assertEquals(1, payment.getTableNumber());
    }

    @Test
    void testToString() {
        Payment payment = new Payment(1, PaymentType.CASH, 20.0);

        assertEquals("1,CASH,20.0", payment.ourToString());
    }

}