package br.ufpa.refactoring.q5;

// Classe redundante: não adiciona comportamento
public class VIPPasswordResetNotification extends PasswordResetNotification {
    public VIPPasswordResetNotification(String customerName) { super(customerName); }
}
