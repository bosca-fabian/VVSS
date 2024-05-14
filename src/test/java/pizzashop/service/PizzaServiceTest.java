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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PizzaServiceTest {

    @Test
    @Order(1)
    @Timeout(value = 2)
    void addPayment_TC1_ECP() {
        //arrange
        int tableNumber = 3;
        PaymentType type = PaymentType.CASH;
        double amount = 2012.0;

        PaymentRepository paymentRepository = new PaymentRepository();
        MenuRepository menuRepository = new MenuRepository();
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        int noOfElements = pizzaService.getPayments().size();
        //act
        assertDoesNotThrow(() -> pizzaService.addPayment(tableNumber, type, amount));
        //assert
        assert pizzaService.getPayments().size() == noOfElements + 1;
    }

    @Test
    @Order(4)
    void addPayment_TC1_BVA() {
        //arrange
        int tableNumber = 1;
        PaymentType type = PaymentType.CASH;
        double amount = 0.1;

        PaymentRepository paymentRepository = new PaymentRepository();
        MenuRepository menuRepository = new MenuRepository();
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        int noOfElements = pizzaService.getPayments().size();
        //act
        assertDoesNotThrow(() -> pizzaService.addPayment(tableNumber, type, amount));

        //assert
        assert pizzaService.getPayments().size() == noOfElements + 1;
    }

    @Test
    @Order(7)
    void addPayment_TC5_BVA() {
        //arrange
        int tableNumber = 9;
        PaymentType type = PaymentType.CARD;
        double amount = 0.2;

        PaymentRepository paymentRepository = new PaymentRepository();
        MenuRepository menuRepository = new MenuRepository();
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        int noOfElements = pizzaService.getPayments().size();
        String expectedMessage = "Invalid table number";
        //act
        Exception exception = assertThrows(Exception.class, () -> pizzaService.addPayment(tableNumber, type, amount));
        //assert
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(noOfElements, pizzaService.getPayments().size());
    }

    @Test
    @Order(5)
    @DisplayName("Test 2 BVA")
    @Timeout(1)
    void addPayment_TC2_BVA() {
        //arrange
        PaymentRepository mockPaymentRepository = new PaymentRepository();
        MenuRepository mockMenuRepository = new MenuRepository();
        int mockTableNumber = 8;
        PaymentType mockType = PaymentType.CASH;
        double mockAmount = 0.1;
        PizzaService mockPizzaService= new PizzaService(mockMenuRepository, mockPaymentRepository);
        int initialSize = mockPizzaService.getPayments().size();

        //act
        assertDoesNotThrow(() -> mockPizzaService.addPayment(mockTableNumber, mockType, mockAmount));


        //assert
        assertEquals(initialSize + 1, mockPizzaService.getPayments().size());
    }

    @Test
    @Order(2)
    @DisplayName("Test 4 ECP")
    void addPayment_TC4_ECP() {
        //arrange
        PaymentRepository mockPaymentRepository = new PaymentRepository();
        MenuRepository mockMenuRepository = new MenuRepository();
        int mockTableNumber = 0;
        PaymentType mockType = PaymentType.CARD;
        double mockAmount = 222.2;
        PizzaService mockPizzaService= new PizzaService(mockMenuRepository, mockPaymentRepository);
        String expectedMessage = "Invalid table number";

        //act
        Exception exception = assertThrows(Exception.class, () ->
                mockPizzaService.addPayment(mockTableNumber, mockType, mockAmount));

        //assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @Order(3)
    @Disabled
    @SuppressWarnings("all")
    void addPayment_TC5_ECP(){
        int tableNumber = 3;
        PaymentType type = PaymentType.CASH;

        PaymentRepository paymentRepository = new PaymentRepository();
        MenuRepository menuRepository = new MenuRepository();
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        int noOfElements = pizzaService.getPayments().size();
        String expectedMessage = "Invalid table number";
//        Exception exception = assertThrows(Exception.class, () -> pizzaService.addPayment(tableNumber, type, " "));
//        assertEquals(exception.getMessage(), expectedMessage);
        assertEquals(noOfElements, pizzaService.getPayments().size());
    }

    @Test
    @Order(6)
    void addPayment_TC4_BVA(){
        //arrange
        int tableNumber = 0;
        PaymentType type = PaymentType.CASH;
        double amount = 0.1;

        PaymentRepository paymentRepository = new PaymentRepository();
        MenuRepository menuRepository = new MenuRepository();
        PizzaService pizzaService = new PizzaService(menuRepository, paymentRepository);

        int noOfElements = pizzaService.getPayments().size();
        String expectedMessage = "Invalid table number";
        //act
        Exception exception = assertThrows(Exception.class, () -> pizzaService.addPayment(tableNumber, type, amount));

        //assert
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(noOfElements, pizzaService.getPayments().size());
    }

    @Test
    @Order(8)
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

        assertEquals(172.0, result);
    }

    @Test
    @Order(9)
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
    @Disabled
    void skip_method() {}

}