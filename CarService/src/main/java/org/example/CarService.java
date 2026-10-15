package org.example;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private final List<Client> clients = new ArrayList<>();
    private final List<Car> cars = new ArrayList<>();
    private final List<Mechanic> mechanics = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    // Реєстрація клієнта
    public void registerClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Клієнт не може бути нульовим.");
        }
        clients.add(client);
    }

    // Реєстрація автомобіля з перевіркою унікальності VIN-коду
    public void registerCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Автомобіль не може бути нульовим.");
        }
        for (Car existingCar : cars) {
            if (existingCar.getVin().equalsIgnoreCase(car.getVin())) {
                throw new IllegalArgumentException("Автомобіль із VIN-кодом '" + car.getVin() + "' вже зареєстрований в автосервісі.");
            }
        }
        cars.add(car);
    }

    // Реєстрація механіка
    public void registerMechanic(Mechanic mechanic) {
        if (mechanic == null) {
            throw new IllegalArgumentException("Механік не може бути нульовим.");
        }
        mechanics.add(mechanic);
    }

    // Створення замовлення для автомобіля
    public Order createOrder(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Неможливо створити замовлення без автомобіля.");
        }
        // Перевіримо, чи зареєстроване авто в сервісі
        if (!cars.contains(car)) {
            throw new IllegalArgumentException("Цей автомобіль не зареєстрований у сервісі.");
        }
        Order order = new Order(car);
        orders.add(order);
        return order;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Mechanic> getMechanics() {
        return mechanics;
    }

    public List<Order> getOrders() {
        return orders;
    }
}