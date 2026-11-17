package br.ufpa.refactoring.q2;

public class PasswordPolicy {

    public boolean isValid(String password) {
        return hasMinLength(password) && hasUpper(password) && hasDigit(password) && hasNoSpaces(password);
    }

    boolean hasMinLength(String p) { return p != null && p.length() >= 10; }
    boolean hasUpper(String p) { return p.chars().anyMatch(Character::isUpperCase); }
    boolean hasDigit(String p) { return p.chars().anyMatch(Character::isDigit); }
    boolean hasNoSpaces(String p) { return !p.contains(" "); }
}
