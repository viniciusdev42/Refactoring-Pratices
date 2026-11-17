package br.ufpa.refactoring.q9;

public class Order {

    private final Customer customer;
    private final double total;
    private final double discountRate;

    public Order(Customer customer, double total, double discountRate) {
        this.customer = customer;
        this.total = total;
        this.discountRate = discountRate;
    }

    public double finalTotal() {
        double discount = total * discountRate;
        if (customer.isVip()) discount += 25.0;
        if (customer.yearsAsCustomer() >= 5) discount += 15.0;
        return Math.max(0.0, total - discount);
    }

    public record Customer(boolean vip, int yearsAsCustomer) {
        public boolean isVip() { return vip; }
    }
}
