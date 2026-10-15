package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final Car car;
    private final List<Job> jobs;
    private Mechanic mechanic;
    private OrderStatus status;

    public Order(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Замовлення повинно належати автомобілю");
        }
        this.car = car;
        this.jobs = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    public Car getCar() {
        return car;
    }

    public List<Job> getJobs() {
        return Collections.unmodifiableList(jobs);
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addJob(Job job) {
        if (status == OrderStatus.COMPLETED || status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Неможливо змінити завершене або скасоване замовлення");
        }
        if (job == null) {
            throw new IllegalArgumentException("Робота не може бути нульовою");
        }
        jobs.add(job);
    }

    public void diagnose() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Діагностику можна виконати лише для замовлення зі статусом CREATED");
        }
        this.status = OrderStatus.DIAGNOSED;
    }

    public void approve() {
        if (status != OrderStatus.CREATED && status != OrderStatus.DIAGNOSED) {
            throw new IllegalStateException("Погодити можна лише замовлення в статусі CREATED або DIAGNOSED");
        }
        if (jobs.isEmpty()) {
            throw new IllegalStateException("Неможливо погодити порожнє замовлення (немає жодної роботи)");
        }
        this.status = OrderStatus.APPROVED;
    }

    public void assignMechanic(Mechanic mechanic) {
        if (status == OrderStatus.COMPLETED || status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Неможливо призначити механіка на завершене або скасоване замовлення");
        }
        if (mechanic == null) {
            throw new IllegalArgumentException("Механік не може бути нульовим");
        }
        if (mechanic.isBusy()) {
            throw new IllegalStateException("Цей механік уже зайнятий іншим активним замовленням");
        }

        if (this.mechanic != null) {
            this.mechanic.setBusy(false);
        }

        this.mechanic = mechanic;
        mechanic.setBusy(true);
    }

    public void startWork() {
        if (status != OrderStatus.APPROVED) {
            throw new IllegalStateException("Розпочати виконання можна лише для погодженого замовлення (APPROVED)");
        }
        if (mechanic == null) {
            throw new IllegalStateException("Неможливо розпочати замовлення без призначеного механіка");
        }
        this.status = OrderStatus.IN_PROGRESS;
    }

    public void completeJob(int index) {
        if (status != OrderStatus.IN_PROGRESS) {
            throw new IllegalStateException("Змінювати статус робіт можна лише під час виконання замовлення (IN_PROGRESS)");
        }
        if (index < 0 || index >= jobs.size()) {
            throw new IllegalArgumentException("Некоректний індекс роботи");
        }
        jobs.get(index).setCompleted(true);
    }

    public void completeOrder() {
        if (status != OrderStatus.IN_PROGRESS) {
            throw new IllegalStateException("Завершити можна лише замовлення, яке виконується (IN_PROGRESS)");
        }
        for (Job job : jobs) {
            if (!job.isCompleted()) {
                throw new IllegalStateException("Неможливо завершити замовлення: не всі роботи позначені виконаними");
            }
        }
        this.status = OrderStatus.COMPLETED;
        if (mechanic != null) {
            mechanic.setBusy(false);
        }
    }

    public void cancel() {
        if (status == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Неможливо скасувати вже завершене замовлення");
        }
        this.status = OrderStatus.CANCELLED;
        if (mechanic != null) {
            mechanic.setBusy(false);
        }
    }

    public BigDecimal getTotalCost() {
        BigDecimal total = BigDecimal.ZERO;
        for (Job job : jobs) {
            total = total.add(job.getTotalCost());
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{car=" + car.getVin() + ", status=" + status + ", mechanic=" + (mechanic != null ? mechanic.getName() : "None") + ", totalCost=" + getTotalCost() + "}";
    }
}