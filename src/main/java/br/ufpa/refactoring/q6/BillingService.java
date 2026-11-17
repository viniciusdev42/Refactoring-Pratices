package br.ufpa.refactoring.q6;

public interface Plan {

    double calculatePrice(int activeUsers, boolean prioritySupport);
}

public class BillingService {

    public double monthlyPrice(
            Plan plan,
            int activeUsers,
            boolean prioritySupport) {

        double price = plan.calculatePrice(activeUsers, prioritySupport);

        return Math.round(price * 100.0) / 100.0;
    }
}

public class FreePlan implements Plan {

    @Override
    public double calculatePrice(int activeUsers, boolean prioritySupport) {
        return 0.0;
    }
}

public class StudentPlan implements Plan {

    @Override
    public double calculatePrice(int activeUsers, boolean prioritySupport) {
        double price = 19.90;

        if (prioritySupport) {
            price += 59.90;
        }

        return price;
    }
}

public interface Plan {

    double calculatePrice(int activeUsers, boolean prioritySupport);
}

public class FreePlan implements Plan {

    @Override
    public double calculatePrice(int activeUsers, boolean prioritySupport) {
        return 0.0;
    }
}

public class StudentPlan implements Plan {

    @Override
    public double calculatePrice(int activeUsers, boolean prioritySupport) {
        double price = 19.90;

        if (prioritySupport) {
            price += 59.90;
        }

        return price;
    }
}

public class ProPlan implements Plan {

    @Override
    public double calculatePrice(int activeUsers, boolean prioritySupport) {
        double price = 49.90 + (activeUsers * 1.25);

        if (prioritySupport) {
            price += 59.90;
        }

        if (activeUsers > 200) {
            price *= 0.95;
        }

        return price;
    }
}

public class EnterprisePlan implements Plan {

    @Override
    public double calculatePrice(int activeUsers, boolean prioritySupport) {
        double price = 199.90 + (activeUsers * 0.90);

        if (prioritySupport) {
            price += 59.90;
        }

        return price;
    }
}