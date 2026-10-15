package org.example;

public class Car {
    private final String vin;
    private final String brand;
    private final String model;
    private final int year;
    private final Client owner;

    public Car(String vin, String brand, String model, int year, Client owner) {
        if (vin == null || vin.isBlank()) {
            throw new IllegalArgumentException("VIN-код автомобіля не може бути порожнім.");
        }
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Марка автомобіля не може бути порожньою.");
        }
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Модель автомобіля не може бути порожньою.");
        }
        if (year < 1900 || year > 2030) {
            throw new IllegalArgumentException("Некоректний рік випуску автомобіля.");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Автомобіль повинен мати власника.");
        }
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.owner = owner;
    }

    public String getVin() {
        return vin;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Client getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Car{vin='" + vin + "', brand='" + brand + "', model='" + model + "', year=" + year + ", owner=" + owner.getName() + "}";
    }
}