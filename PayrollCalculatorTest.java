package br.ufpa.refactoring.q3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayrollCalculatorTest {

    @Test
    void netSalary_examples() {
        PayrollCalculator calc = new PayrollCalculator();

        var e1 = new PayrollCalculator.Employee(
                2800,
                List.of(new PayrollCalculator.Overtime(10, 25)),
                true,
                120
        );
        double salary1 = calc.monthlyNetSalary(e1);
        assertEquals(2800 + (10*25) - ((2800 + (10*25))*0.08) - 120 + 450, salary1, 0.01);

        var e2 = new PayrollCalculator.Employee(
                7500,
                List.of(),
                false,
                200
        );
        double salary2 = calc.monthlyNetSalary(e2);
        assertEquals(7500 - (7500*0.18) - 200, salary2, 0.01);
    }

    @Test
    void refactorGoal_replaceTempsWithQueries() {
        // TODO: após refatorar, use queries (métodos) no lugar de muitos temporários.
        // Testes acima devem continuar passando.
    }
}
