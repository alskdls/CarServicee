package org.example;

public class Client {
    private final String name;
    private final String phone;

    public Client(String name, String phone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім'я клієнта не може бути порожнім");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Телефон клієнта не може бути порожнім");
        }
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Client{name='" + name + "', phone='" + phone + "'}";
    }
}