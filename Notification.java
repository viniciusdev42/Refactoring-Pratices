package br.ufpa.refactoring.q5;

import java.time.LocalDate;

public class Notification {
    private final String customerName;
    public Notification(String customerName) { this.customerName = customerName; }

    public String subject() { return "Redefinição de senha"; }
    public String body() { return "Olá " + customerName + ", clique no link para redefinir."; }
    public String render() {
        return "[" + LocalDate.now() + "] " + subject() + "\n" + body();
    }
}
