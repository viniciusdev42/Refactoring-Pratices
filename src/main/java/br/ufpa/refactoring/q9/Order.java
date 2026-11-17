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
        // O cálculo do bônus de fidelidade foi movido para a classe Customer
        double discount = (total * discountRate) + customer.calculateLoyaltyDiscount();
        return Math.max(0.0, total - discount);
    }

    public record Customer(boolean vip, int yearsAsCustomer) {
        public boolean isVip() { return vip; }
        /* Método movido,pois usa os dados da classe Customer */
        public double calculateLoyaltyDiscount() {
            double loyaltyDiscount = 0.0;
            if (vip) loyaltyDiscount += 25.0;
            if (yearsAsCustomer >= 5) loyaltyDiscount += 15.0;
            return loyaltyDiscount;
        }
    }

    /*** Efeitos da Refatoração ****
      
      1.A classe Customer agora é responsável por calcular regras que 
       dependem do seu próprio estado (vip, yearsAsCustomer).

      2. A classe Order fica focada apenas nas regras do pedido (total, discountRate), enquanto Customer cuida das regras do cliente.
    */
}