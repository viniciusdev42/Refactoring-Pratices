package br.ufpa.refactoring.q8;

class ContactInfo {
    private String email;
    private String phone;
    private String city;
    private String zip;

    ContactInfo(String email, String phone, String city, String zip) {
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.zip = zip;
    }

    public boolean isValid() {
        if (email == null || !email.contains("@")) return false;
        if (phone == null || phone.length() < 10) return false;
        if (zip == null || !Pattern.matches("\\d{5}-\\d{3}", zip)) return false;
        return true;
    }

    public String format(String name) {
        return name + " | " + email + " | " + phone + " | " + city + " - " + zip;
    }

    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCity() { return city; }
    public String getZip() { return zip; }
}

public class StudentRegistration {

    private String name;
    private ContactInfo contactInfo; // Delegação para a nova classe

    public StudentRegistration(String name, String email, String phone, String city, String zip) {
        this.name = name;
        this.contactInfo = new ContactInfo(email, phone, city, zip);
    }

    public boolean isValid() {
        if (name == null || name.isBlank()) return false;
        return contactInfo.isValid();
    }

    public String formattedContact() {
        return contactInfo.format(name);
    }
}