package br.ufpa.refactoring.q6;

public class BillingService {

    public enum Plan { FREE, STUDENT, PRO, ENTERPRISE }

    public double monthlyPrice(Plan plan, int activeUsers, boolean prioritySupport) {
        double price;
        switch (plan) {
            case FREE -> price = 0.0;
            case STUDENT -> price = 19.90;
            case PRO -> price = 49.90 + (activeUsers * 1.25);
            case ENTERPRISE -> price = 199.90 + (activeUsers * 0.90);
            default -> throw new IllegalArgumentException();
        }
        if (prioritySupport && plan != Plan.FREE) price += 59.90;
        if (activeUsers > 200 && plan == Plan.PRO) price *= 0.95;
        return Math.round(price * 100.0) / 100.0;
    }
}
