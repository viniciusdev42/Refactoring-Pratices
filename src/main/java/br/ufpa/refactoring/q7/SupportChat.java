package br.ufpa.refactoring.q7;

public class SupportChat {

    public String greeting(User user) {
        if (user == null) {
            user = User.NULL;
        }
        return user.getGreeting();
    }

    public interface User {
        String name();
        boolean isBanned();

        default String getGreeting() {
            if (isBanned()) {
                return "Conta bloqueada. Contate o suporte.";
            }
            if (name() == null || name().isBlank()) {
                return "Olá! Como posso ajudar?";
            }
            return "Olá, " + name() + "! Como posso ajudar?";
        }

        User NULL = new NullUser();
    }

    public record RealUser(String name, boolean banned) implements User {
        @Override
        public boolean isBanned() {
            return banned;
        }
    }

    public static final class NullUser implements User {
        @Override
        public String name() {
            return null;
        }

        @Override
        public boolean isBanned() {
            return false;
        }

        @Override
        public String getGreeting() {
            return "Olá! Quer continuar como visitante?";
        }
    }
}