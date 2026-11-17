package br.ufpa.refactoring.q1;

import java.time.LocalDate;
import java.util.List;

public class PurchaseEmailService {

    public String buildEmail(Purchase purchase) {
        StringBuilder sb = new StringBuilder();

        sb.append(buildHeader(purchase));
        
        double subtotal = appendItemsAndGetSubtotal(sb, purchase.items());
        
        double shipping = calculateShipping(purchase.isExpress());
        double discount = calculateDiscount(subtotal, purchase.coupon());
        double total = calculateTotal(subtotal, shipping, discount);
        
        sb.append(buildFinancialSummary(subtotal, shipping, discount, total));
        sb.append(buildAddressSection(purchase.address()));
        sb.append(buildFooter());

        return sb.toString();
    }

    private String buildHeader(Purchase purchase) {
        return "Olá, " + purchase.customerName() + "!\n\n" +
               "Seu pedido #" + purchase.id() + " foi confirmado em " + LocalDate.now() + ".\n\n";
    }

    private double appendItemsAndGetSubtotal(StringBuilder sb, List<PurchaseItem> items) {
        sb.append("Itens:\n");
        double subtotal = 0.0;
        for (PurchaseItem it : items) {
            double line = it.unitPrice() * it.quantity();
            subtotal += line;
            sb.append("- ").append(it.name())
              .append(" (").append(it.quantity()).append("x) : R$ ")
              .append(String.format("%.2f", line)).append("\n");
        }
        return subtotal;
    }

    private double calculateShipping(boolean isExpress) {
        return isExpress ? 29.90 : 14.90;
    }

    private double calculateDiscount(double subtotal, String coupon) {
        double discount = 0.0;
        if (subtotal >= 300.0) {
            discount += subtotal * 0.10;
        }
        if (coupon != null && coupon.startsWith("VIP")) {
            discount += 20.0;
        }
        return discount;
    }

    private double calculateTotal(double subtotal, double shipping, double discount) {
        return subtotal + shipping - discount;
    }

    private String buildFinancialSummary(double subtotal, double shipping, double discount, double total) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSubtotal: R$ ").append(String.format("%.2f", subtotal)).append("\n");
        sb.append("Frete: R$ ").append(String.format("%.2f", shipping)).append("\n");
        sb.append("Descontos: R$ ").append(String.format("%.2f", discount)).append("\n");
        sb.append("TOTAL: R$ ").append(String.format("%.2f", total)).append("\n\n");
        return sb.toString();
    }

    private String buildAddressSection(Address address) {
        return "Endereço de entrega:\n" +
               address.street() + ", " + address.number() + "\n" +
               address.city() + " - " + address.zip() + "\n\n";
    }

    private String buildFooter() {
        return "Obrigado por comprar com a gente!\n";
    }

    public record Purchase(String id, String customerName, List<PurchaseItem> items,
                           boolean isExpress, String coupon, Address address) {}

    public record PurchaseItem(String name, int quantity, double unitPrice) {}

    public record Address(String street, String number, String city, String zip) {}
}