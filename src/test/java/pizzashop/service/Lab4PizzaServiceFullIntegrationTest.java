package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

class Lab4PizzaServiceFullIntegrationTest {
    PaymentRepository paymentRepository;
    MenuRepository menuRepository;
    PizzaService pizzaService;

    @BeforeEach
    public void setup() {
        menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository();
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @AfterEach
    public void finish() {
        paymentRepository = null;
        menuRepository = null;
        pizzaService = null;
    }

    @Test
    public void testAdd() {
        Payment payment = new Payment(1, PaymentType.CASH, 20.0);
        int initialSize = pizzaService.getPayments().size();

        assertDoesNotThrow(
                () -> pizzaService.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount()));
        assertEquals(1, pizzaService.getPayments().get(initialSize).getTableNumber());
        assertEquals(PaymentType.CASH, pizzaService.getPayments().get(initialSize).getType());
        assertEquals(20.0, pizzaService.getPayments().get(initialSize).getAmount());
    }

    @Test
    public void testAddWrongTableNumber() {
        Payment payment = new Payment(9, PaymentType.CARD, 100.0);

        assertThrows(Exception.class,
                () -> pizzaService.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount()));
    }
}