package br.ufpa.refactoring.q9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void finalTotal_examples() {
        var vip = new Order.Customer(true, 6);
        var o1 = new Order(vip, 200.0, 0.10);
        assertEquals(140.0, o1.finalTotal(), 0.001);

        var regular = new Order.Customer(false, 1);
        var o2 = new Order(regular, 200.0, 0.10);
        assertEquals(180.0, o2.finalTotal(), 0.001);
    }

    @Test
    void refactorGoal_moveDiscountLogic_toCustomerOrPolicy() {
      // Testa se a lógica de desconto de fidelidade foi corretamente movida/delegada para o Customer:
        
        // 1. VIP com 6 anos (VIP: 25.0 + Fidelidade >= 5 anos: 15.0 = 40.0)
        var vipLoyalCustomer = new Order.Customer(true, 6);
        assertEquals(40.0, vipLoyalCustomer.calculateLoyaltyDiscount(), 0.001);

        // 2. VIP com menos de 5 anos (Apenas VIP: 25.0)
        var vipNewCustomer = new Order.Customer(true, 3);
        assertEquals(25.0, vipNewCustomer.calculateLoyaltyDiscount(), 0.001);

        // 3. Não-VIP com 5 ou mais anos (Apenas Fidelidade: 15.0)
        var regularLoyalCustomer = new Order.Customer(false, 5);
        assertEquals(15.0, regularLoyalCustomer.calculateLoyaltyDiscount(), 0.001);

        // 4. Não-VIP com menos de 5 anos (Sem bônus: 0.0)
        var regularNewCustomer = new Order.Customer(false, 2);
        assertEquals(0.0, regularNewCustomer.calculateLoyaltyDiscount(), 0.001);
    }
}
