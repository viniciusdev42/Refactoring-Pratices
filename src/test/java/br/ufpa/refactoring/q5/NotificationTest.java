package br.ufpa.refactoring.q5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @Test
    void render_shouldContainSubjectAndBody() {
        Notification n = new VIPPasswordResetNotification("Joelma");
        String r = n.render();

        assertTrue(r.contains("Redefinição de senha"));
        assertTrue(r.contains("Olá Joelma"));
    }

    @Test
    void refactorGoal_collapseHierarchy_removeRedundantSubclass() {
        Notification n = new PasswordResetNotification("Joelma");
        String r = n.render();

        // Asserts de comportamento que garantem que a renderização continua correta
        assertNotNull(r);
        assertTrue(r.contains("Redefinição de senha"));
        assertTrue(r.contains("Olá Joelma"));
        assertTrue(r.contains("clique no link para redefinir"));
    }
}
