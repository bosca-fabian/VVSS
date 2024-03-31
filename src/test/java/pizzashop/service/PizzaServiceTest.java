package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        pizzaService.addPayment(tableNumber, type, amount);

        //assert
        assert pizzaService.getPayments().size() == noOfElements + 1;
    }

    @Test
    @Order(2)
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
        pizzaService.addPayment(tableNumber, type, amount);

        //assert
        assert pizzaService.getPayments().size() == noOfElements + 1;
    }

    @Test
    @Order(3)
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
        assertEquals(exception.getMessage(), expectedMessage);
        assertEquals(noOfElements, pizzaService.getPayments().size());
    }

    @Test
    @Disabled
    void skip_method() {}

}