package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

class Lab4PizzaServiceTest {


    @InjectMocks
    private PizzaService pizzaService;
    @Mock
    private PaymentRepository mockPaymentRepository;
    @Mock
    private MenuRepository mockMenuRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        pizzaService = new PizzaService(mockMenuRepository, mockPaymentRepository);
    }

    @AfterEach
    public void finish() {
        pizzaService = null;
    }

    @Test
    void testAdd() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        when(mockPaymentRepository.getAll()).thenReturn(paymentList);
        doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            Payment payment = (Payment) args[0];

            paymentList.add(payment);
            return null;
        }).when(mockPaymentRepository).add(any(Payment.class));

        pizzaService.addPayment(1, PaymentType.CASH, 20.0);

        assertEquals(1, mockPaymentRepository.getAll().size());
        assertEquals(1, mockPaymentRepository.getAll().get(0).getTableNumber());
        assertEquals(PaymentType.CASH, mockPaymentRepository.getAll().get(0).getType());
        assertEquals(20.0, mockPaymentRepository.getAll().get(0).getAmount());
    }

    @Test
    void testAddWrongTableNumber() {
        List<Payment> paymentList = new ArrayList<>();
        when(mockPaymentRepository.getAll()).thenReturn(paymentList);
        doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            Payment payment = (Payment) args[0];

            paymentList.add(payment);
            return null;
        }).when(mockPaymentRepository).add(any(Payment.class));

        assertThrows(Exception.class, () -> pizzaService.addPayment(10, PaymentType.CASH, 25.0));
    }



}