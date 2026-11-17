package br.ufpa.refactoring.q8;

import java.util.regex.Pattern;

public class StudentRegistration {

    private String name;
    private String email;
    private String phone;
    private String city;
    private String zip;

    public StudentRegistration(String name, String email, String phone, String city, String zip) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.zip = zip;
    }

    public boolean isValid() {
        if (name == null || name.isBlank()) return false;
        if (email == null || !email.contains("@")) return false;
        if (phone == null || phone.length() < 10) return false;
        if (zip == null || !Pattern.matches("\\d{5}-\\d{3}", zip)) return false;
        return true;
    }

    public String formattedContact() {
        return name + " | " + email + " | " + phone + " | " + city + " - " + zip;
    }
}
