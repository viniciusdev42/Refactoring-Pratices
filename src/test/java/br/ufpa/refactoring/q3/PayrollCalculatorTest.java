package br.ufpa.refactoring.q3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayrollCalculatorTest {

    @Test
    void netSalary_examples() {
        PayrollCalculator calc = new PayrollCalculator();
        
        // Ajustado para base 2700 + 250 (overtime) = 2950,que é menor que 3000 (logo aplica 8 % de imposto)
        var e1 = new PayrollCalculator.Employee(
                2700,
                List.of(new PayrollCalculator.Overtime(10, 25)),
                true,
                120
        );
        double salary1 = calc.monthlyNetSalary(e1);
        assertEquals(2700 + (10*25) - ((2700 + (10*25))*0.08) - 120 + 450, salary1, 0.01);
        
        // Funcionário com salário bruto de 7500 (aplica 18% de imposto)
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
        PayrollCalculator calc = new PayrollCalculator();

        // Cenário 1: Salário bruto na faixa intermediária (entre 3000 e 7000 -> imposto de 12%)
        var employeeMidTax = new PayrollCalculator.Employee(
                4000,
                List.of(new PayrollCalculator.Overtime(5, 50)), // Bruto = 4000 + 250 = 4250
                true,  // Tem auxílio refeição (+450)
                150    // Dedução de plano de saúde (-150)
        );
        // Bruto = 4250
        // Imposto (12%) = 4250 * 0.12 = 510
        // Líquido = 4250 - 510 - 150 + 450 = 4040.0
        double netSalaryMid = calc.monthlyNetSalary(employeeMidTax);
        assertEquals(4040.0, netSalaryMid, 0.01, "Deve calcular corretamente para a faixa de imposto de 12% com auxílio e plano");

        // Cenário 2: Salário bruto na faixa mais alta (> 7000 -> imposto de 18%) com múltiplas horas extras
        var employeeHighTax = new PayrollCalculator.Employee(
                8000,
                List.of(
                        new PayrollCalculator.Overtime(10, 50), // 500
                        new PayrollCalculator.Overtime(5, 40)   // 200
                ), // Bruto = 8000 + 700 = 8700
                false, // Sem auxílio refeição
                300    // Dedução de plano de saúde
        );
        // Bruto = 8700
        // Imposto (18%) = 8700 * 0.18 = 1566
        // Líquido = 8700 - 1566 - 300 + 0 = 6834.0
        double netSalaryHigh = calc.monthlyNetSalary(employeeHighTax);
        assertEquals(6834.0, netSalaryHigh, 0.01, "Deve calcular corretamente com múltiplas horas extras e faixa de 18%");
    }
}
