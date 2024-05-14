package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Lab4PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setup() {
        paymentRepository = new PaymentRepository();
    }

    @AfterEach
    public void finish() {
        paymentRepository = null;
    }

    @Test
    public void testAdd() {
        Payment mockPayment = mock(Payment.class);
        when(mockPayment.ourToString()).thenReturn("1,CASH,20.0");
        int initialSize = paymentRepository.getAll().size();

        paymentRepository.add(mockPayment);

        assertEquals(initialSize + 1, paymentRepository.getAll().size());
        assertEquals("1,CASH,20.0", paymentRepository.getAll().get(initialSize).ourToString());
    }

    @Test
    public void testWriteAll() {
        Payment mockPayment = mock(Payment.class);
        when(mockPayment.ourToString()).thenReturn("1,CASH,20.0");

        paymentRepository.add(mockPayment);

        verify(mockPayment, times(2)).ourToString();
    }

}