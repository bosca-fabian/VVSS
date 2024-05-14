package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class PizzaServiceTestWBT {

    @Test
    void getTotalAmount_F02_TC01() {
        MenuRepository menuRepository = new MenuRepository();
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        when(paymentRepository.getAll()).thenReturn(null);
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        double result = pizzaService.getTotalAmount(PaymentType.CASH);

        assertEquals(0.0f, result);
    }

    @Test
    void getTotalAmount_F02_TC02() {
        MenuRepository menuRepository = new MenuRepository();
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        List<Payment> payments = new ArrayList<>();
        when(paymentRepository.getAll()).thenReturn(payments);
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        double result = pizzaService.getTotalAmount(PaymentType.CASH);

        assertEquals(0.0f, result);
        assert pizzaService.getPayments() != null;
    }

    @Test
    void getTotalAmount_F02_TC03() {
        MenuRepository menuRepository = new MenuRepository();
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.CASH, 23.0));
        payments.add(new Payment(2, PaymentType.CASH, 54.0));
        payments.add(new Payment(3, PaymentType.CASH, 65.0));
        when(paymentRepository.getAll()).thenReturn(payments);
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        double result = pizzaService.getTotalAmount(PaymentType.CASH);

        assertEquals(142.0, result);
    }

    @Test
    void getTotalAmount_F02_TC04() {
        MenuRepository menuRepository = new MenuRepository();
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.CASH, 23.0));
        payments.add(new Payment(2, PaymentType.CASH, 54.0));
        payments.add(new Payment(3, PaymentType.CASH, 65.0));
        when(paymentRepository.getAll()).thenReturn(payments);
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        double result = pizzaService.getTotalAmount(PaymentType.CARD);

        assertEquals(0.0f, result);
    }

    @Test
    void getTotalAmount_F02_TC05() {
        MenuRepository menuRepository = new MenuRepository();
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.CASH, 23.0));
        payments.add(null);
        payments.add(new Payment(3, PaymentType.CASH, 65.0));
        when(paymentRepository.getAll()).thenReturn(payments);
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        assertThrows(NullPointerException.class,() -> pizzaService.getTotalAmount(PaymentType.CARD));
    }

}