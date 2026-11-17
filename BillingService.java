package br.ufpa.refactoring.q6;

public class BillingService {
    
    public enum  Plan {Free,Student,Pro,Enterprise};

    public double monthlyPrice(Plan plan, int activeUsers, boolean prioritySupport) {
        double price;
        switch (plan) {
            case Free -> price = 0.0;
            case Student -> price = 19.90;
            case Pro -> price = 49.90 + (activeUsers * 1.25);
            case Enterprise -> price = 199.90 + (activeUsers * 0.90);
            default -> throw new IllegalArgumentException();
        }
        if (prioritySupport && plan != Plan.Free) price += 59.90;
        if (activeUsers > 200 && plan == Plan.Pro) price *= 0.95;
        return Math.round(price * 100.0) / 100.0;
    }
}

class Free extends BillingService {
   
   private double price = 0.0;
   
   public double monthlyPrice(Plan plan, int activeUsers, boolean prioritySupport) {} 
    
}

class Student extends BillingService {

    private double price = 0.0;

  public double monthlyPrice(Plan plan, int activeUsers, boolean prioritySupport) {}
}

class Pro extends BillingService {

    private double price = 0.0;


   public double monthlyPrice(Plan plan, int activeUsers, boolean prioritySupport) {}
}

class Enterprise extends BillingService {
   
    private double price = 0.0;

    public double monthlyPrice(Plan plan, int activeUsers, boolean prioritySupport) {} 
}

