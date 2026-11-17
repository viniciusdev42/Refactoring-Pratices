package br.ufpa.refactoring.q6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillingServiceTest {

    @Test
    void priceTable_examples() {
        BillingService svc = new BillingService();

        assertEquals(0.0, svc.monthlyPrice(BillingService.Plan.FREE, 10, false), 0.001);
        assertEquals(19.90, svc.monthlyPrice(BillingService.Plan.STUDENT, 10, false), 0.001);

        assertEquals(49.90 + 10*1.25, svc.monthlyPrice(BillingService.Plan.PRO, 10, false), 0.01);
        assertEquals((49.90 + 250*1.25) * 0.95, svc.monthlyPrice(BillingService.Plan.PRO, 250, false), 0.01);

        assertEquals(199.90 + 10*0.90 + 59.90, svc.monthlyPrice(BillingService.Plan.ENTERPRISE, 10, true), 0.01);
    }

    @Test
    void refactorGoal_replaceConditionalWithPolymorphism() {
        // Valida o comportamento polimórfico cobrindo regras específicas de cada plano (como suporte prioritário e descontos)
        BillingService svc = new BillingService();

        Plan free = new FreePlan();
        Plan student = new StudentPlan();
        Plan pro = new ProPlan();
        Plan enterprise = new EnterprisePlan();

        // Free: sempre zero, mesmo com suporte ou usuários
        assertEquals(0.0, svc.monthlyPrice(free, 100, true), 0.001);

        // Student: preço base + suporte prioritário
        assertEquals(19.90 + 59.90, svc.monthlyPrice(student, 50, true), 0.001);

        // Pro: acima de 200 usuários aplica 5% de desconto
        assertEquals((49.90 + 201 * 1.25) * 0.95, svc.monthlyPrice(pro, 201, false), 0.01);

        // Enterprise: cálculo padrão com suporte prioritário
        assertEquals(199.90 + 50 * 0.90 + 59.90, svc.monthlyPrice(enterprise, 50, true), 0.01);
    }
}
