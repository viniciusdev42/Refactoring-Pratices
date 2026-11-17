package br.ufpa.refactoring.q3;

import java.util.List;

public class PayrollCalculator {

    public double monthlyNetSalary(Employee e) {
        double gross = calculateGross(e);
        double tax = calculateTax(gross);
        double healthPlan = e.healthPlanDeduction();
        double mealVoucher = calculateMealVoucher(e);

        double net = gross - tax - healthPlan + mealVoucher;
        return Math.round(net * 100.0) / 100.0;
    }

    private double calculateGross(Employee e) {
        return e.baseSalary() + calculateOvertime(e.overtimes());
    }

    private double calculateOvertime(List<Overtime> overtimes) {
        double total = 0.0;
        for (Overtime ot : overtimes) {
            total += ot.hours() * ot.hourRate();
        }
        return total;
    }

    private double calculateTax(double gross) {
        if (gross <= 3000) {
            return gross * 0.08;
        } else if (gross <= 7000) {
            return gross * 0.12;
        } else {
            return gross * 0.18;
        }
    }

    private double calculateMealVoucher(Employee e) {
        return e.hasMealVoucher() ? 450.0 : 0.0;
    }

    public record Employee(double baseSalary, List<Overtime> overtimes,
                           boolean hasMealVoucher, double healthPlanDeduction) {}

    public record Overtime(int hours, double hourRate) {}
}