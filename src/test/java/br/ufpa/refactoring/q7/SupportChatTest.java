package br.ufpa.refactoring.q7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupportChatTest {

    @Test
    void greeting_examples() {
        SupportChat chat = new SupportChat();

        assertEquals("Olá! Quer continuar como visitante?", chat.greeting(null));
        assertEquals("Conta bloqueada. Contate o suporte.",
                chat.greeting(new SupportChat.User("Ana", true)));
        assertEquals("Olá! Como posso ajudar?",
                chat.greeting(new SupportChat.User("  ", false)));
        assertEquals("Olá, Victor! Como posso ajudar?",
                chat.greeting(new SupportChat.User("Victor", false)));
    }

    @Test
    void refactorGoal_introduceNullObject_removeNullCheck() {
       SupportChat chat = new SupportChat();

        // Validação de comportamento completo sem o uso de if(user == null) explícito
        assertAll(
            () -> assertEquals("Olá! Quer continuar como visitante?", chat.greeting(null)),
            () -> assertEquals("Conta bloqueada. Contate o suporte.", chat.greeting(new SupportChat.RealUser("Ana", true))),
            () -> assertEquals("Olá! Como posso ajudar?", chat.greeting(new SupportChat.RealUser("  ", false))),
            () -> assertEquals("Olá, Victor! Como posso ajudar?", chat.greeting(new SupportChat.RealUser("Victor", false)))
        );
    }
}
