package org.example;

public class Mechanic {
    private final String name;
    private final String specialization;
    private boolean busy;

    public Mechanic(String name, String specialization) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім'я механіка не може бути порожнім");
        }
        this.name = name;
        this.specialization = specialization;
        this.busy = false;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public String toString() {
        return "Mechanic{name='" + name + "', specialization='" + specialization + "', busy=" + busy + "}";
    }
}