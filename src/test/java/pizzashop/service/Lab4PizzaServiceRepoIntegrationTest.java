package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Lab4PizzaServiceRepoIntegrationTest {

    PaymentRepository paymentRepository;
    @Mock
    MenuRepository menuRepository;
    @InjectMocks
    PizzaService pizzaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        paymentRepository = new PaymentRepository();
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @AfterEach
    public void finish() {
        paymentRepository = null;
        pizzaService = null;
    }

    @Test
    public void testAdd() {
        Payment mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(1);
        when(mockPayment.getType()).thenReturn(PaymentType.CASH);
        when(mockPayment.getAmount()).thenReturn(20.0);
        int initialSize = pizzaService.getPayments().size();

        assertDoesNotThrow(
                () -> pizzaService.addPayment(mockPayment.getTableNumber(), mockPayment.getType(), mockPayment.getAmount()));
        assertEquals(1, pizzaService.getPayments().get(initialSize).getTableNumber());
        assertEquals(PaymentType.CASH, pizzaService.getPayments().get(initialSize).getType());
        assertEquals(20.0, pizzaService.getPayments().get(initialSize).getAmount());
    }

    @Test
    void testAddWrongTableNumber() {
        Payment mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(-10);
        when(mockPayment.getType()).thenReturn(PaymentType.CASH);
        when(mockPayment.getAmount()).thenReturn(20.0);

        assertThrows(Exception.class,
                () -> pizzaService.addPayment(mockPayment.getTableNumber(), mockPayment.getType(), mockPayment.getAmount()));
    }

}