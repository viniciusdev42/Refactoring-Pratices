package br.ufpa.refactoring.q7;

public class SupportChat {

    public String greeting(User user) {
        if (user == null) return "Olá! Quer continuar como visitante?";
        if (user.isBanned()) return "Conta bloqueada. Contate o suporte.";
        if (user.name() == null || user.name().isBlank()) return "Olá! Como posso ajudar?";
        return "Olá, " + user.name() + "! Como posso ajudar?";
    }

    public record User(String name, boolean banned) {
        public boolean isBanned() { return banned; }
    }
}
