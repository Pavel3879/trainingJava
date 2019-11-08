package lesson23.contact.phone.directory;

public class Contact {
    String phone;
    String email;

    public Contact(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("{phone: %s email: %s}", phone, email);
    }
}
