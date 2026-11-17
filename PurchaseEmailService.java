package br.ufpa.refactoring.q1;

import java.time.LocalDate;
import java.util.List;

public class PurchaseEmailService {

    public String buildEmail(Purchase purchase) {
        StringBuilder sb = new StringBuilder();

        sb.append("Olá, ").append(purchase.customerName()).append("!\n\n");
        sb.append("Seu pedido #").append(purchase.id()).append(" foi confirmado em ")
          .append(LocalDate.now()).append(".\n\n");

        sb.append("Itens:\n");
        double subtotal = 0.0;
        for (PurchaseItem it : purchase.items()) {
            double line = it.unitPrice() * it.quantity();
            subtotal += line;
            sb.append("- ").append(it.name())
              .append(" (").append(it.quantity()).append("x) : R$ ")
              .append(String.format("%.2f", line)).append("\n");
        }

        double shipping = purchase.isExpress() ? 29.90 : 14.90;
        double discount = 0.0;
        if (subtotal >= 300.0) discount = subtotal * 0.10;
        if (purchase.coupon() != null && purchase.coupon().startsWith("VIP"))
            discount += 20.0;

        double total = subtotal + shipping - discount;

        sb.append("\nSubtotal: R$ ").append(String.format("%.2f", subtotal)).append("\n");
        sb.append("Frete: R$ ").append(String.format("%.2f", shipping)).append("\n");
        sb.append("Descontos: R$ ").append(String.format("%.2f", discount)).append("\n");
        sb.append("TOTAL: R$ ").append(String.format("%.2f", total)).append("\n\n");

        sb.append("Endereço de entrega:\n");
        sb.append(purchase.address().street()).append(", ")
          .append(purchase.address().number()).append("\n");
        sb.append(purchase.address().city()).append(" - ")
          .append(purchase.address().zip()).append("\n\n");

        sb.append("Obrigado por comprar com a gente!\n");
        return sb.toString();
    }

    public record Purchase(String id, String customerName, List<PurchaseItem> items,
                           boolean isExpress, String coupon, Address address) {}

    public record PurchaseItem(String name, int quantity, double unitPrice) {}

    public record Address(String street, String number, String city, String zip) {}
}
