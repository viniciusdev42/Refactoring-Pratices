package br.ufpa.refactoring.q3;

import java.util.List;

public class PayrollCalculator {

    public double monthlyNetSalary(Employee e) {
        double base = e.baseSalary();
        double overtime = 0.0;
        for (Overtime ot : e.overtimes()) overtime += ot.hours() * ot.hourRate();

        double gross = base + overtime;
        double tax;
        if (gross <= 3000) tax = gross * 0.08;
        else if (gross <= 7000) tax = gross * 0.12;
        else tax = gross * 0.18;

        double mealVoucher = e.hasMealVoucher() ? 450.0 : 0.0;
        double healthPlan = e.healthPlanDeduction();

        double net = gross - tax - healthPlan + mealVoucher;
        return Math.round(net * 100.0) / 100.0;
    }

    public record Employee(double baseSalary, List<Overtime> overtimes,
                           boolean hasMealVoucher, double healthPlanDeduction) {}

    public record Overtime(int hours, double hourRate) {}
}
