package br.ufpa.refactoring.q2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyTest {

    @Test
    void validPassword_examples() {
        PasswordPolicy policy = new PasswordPolicy();
        assertTrue(policy.isValid("SenhaForte1"));
        assertTrue(policy.isValid("Abcdefghi1")); // Testa o comprimento máximo de 10 caracteres
    }

    @Test
    void invalidPassword_examples() {
        PasswordPolicy policy = new PasswordPolicy();
        assertFalse(policy.isValid("curta1A"));       // < 10
        assertFalse(policy.isValid("senhaforte1"));   // sem maiúscula
        assertFalse(policy.isValid("SenhaForteX"));   // sem dígito
        assertFalse(policy.isValid("Senha Forte1"));  // espaço
        assertFalse(policy.isValid(""));              // string vazia
        assertFalse(policy.isValid(null));            // valor nulo
    }

    @Test
    void refactorGoal_inlineSomeHelpers() {
        // Garante o comportamento robusto e inalterado após refatorações (ex: inline de helpers)
        PasswordPolicy policy = new PasswordPolicy();
        
        // Verificando os limites rigorosos de tamanho
        assertFalse(policy.isValid("Abcdefgh1"));     // 9 caracteres (inválido)
        assertTrue(policy.isValid("Abcdefghi1"));     // 10 caracteres (válido)

        // Verificando restrições de caracteres específicos
        assertFalse(policy.isValid("abcdefghi1"));    // Apenas minúsculas e dígito (sem maiúscula)
        assertFalse(policy.isValid("ABCDEFGHI1"));    // Apenas maiúsculas e dígito (sem minúscula - embora a regra não exija explicitamente, testa a ausência)
        assertFalse(policy.isValid("SenhaForteAA"));  // Sem dígito
        assertFalse(policy.isValid("SenhaForte 1"));  // Espaço no meio da senha
    }
}
