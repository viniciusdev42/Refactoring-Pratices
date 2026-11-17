package br.ufpa.refactoring.q1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseEmailServiceTest {

    @Test
    void buildEmail_shouldKeepSameOutputAfterRefactor() {
        PurchaseEmailService svc = new PurchaseEmailService();

        var purchase = new PurchaseEmailService.Purchase(
                "123",
                "Victor",
                List.of(
                        new PurchaseEmailService.PurchaseItem("Caderno", 2, 15.0),
                        new PurchaseEmailService.PurchaseItem("Caneta", 3, 3.5)
                ),
                false,
                "VIP-20",
                new PurchaseEmailService.Address("Rua A", "10", "Belém", "66000-000")
        );

        String email = svc.buildEmail(purchase);

        // Asserções parciais de partes críticas do email
        assertTrue(email.contains("Olá, Victor!"));
        assertTrue(email.contains("Seu pedido #123 foi confirmado em " + LocalDate.now() + "."));
        
        // Itens e cálculo de subtotal (30.00 + 10.50 = 40.50)
        assertTrue(email.contains("- Caderno (2x) : R$ " + String.format("%.2f", 30.0)));
        assertTrue(email.contains("- Caneta (3x) : R$ " + String.format("%.2f", 10.5)));

        // Resumo financeiro (Subtotal: 40.50, Frete: 14.90, Descontos: 20.00, Total: 35.40)
        assertTrue(email.contains("Subtotal: R$ " + String.format("%.2f", 40.5)));
        assertTrue(email.contains("Frete: R$ " + String.format("%.2f", 14.90)));
        assertTrue(email.contains("Descontos: R$ " + String.format("%.2f", 20.0)));
        assertTrue(email.contains("TOTAL: R$ " + String.format("%.2f", 35.4)));

        // Endereço e Rodapé
        assertTrue(email.contains("Endereço de entrega:\nRua A, 10\nBelém - 66000-000"));
        assertTrue(email.contains("Obrigado por comprar com a gente!"));

        String expectedEmail = "Olá, Victor!\n\n" +
                "Seu pedido #123 foi confirmado em " + LocalDate.now() + ".\n\n" +
                "Itens:\n" +
                "- Caderno (2x) : R$ " + String.format("%.2f", 30.0) + "\n" +
                "- Caneta (3x) : R$ " + String.format("%.2f", 10.5) + "\n" +
                "\nSubtotal: R$ " + String.format("%.2f", 40.5) + "\n" +
                "Frete: R$ " + String.format("%.2f", 14.9) + "\n" +
                "Descontos: R$ " + String.format("%.2f", 20.0) + "\n" +
                "TOTAL: R$ " + String.format("%.2f", 35.4) + "\n\n" +
                "Endereço de entrega:\n" +
                "Rua A, 10\n" +
                "Belém - 66000-000\n\n" +
                "Obrigado por comprar com a gente!\n";

    assertEquals(expectedEmail,email);

   }
}

