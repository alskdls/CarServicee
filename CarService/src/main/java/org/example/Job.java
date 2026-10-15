package org.example;

import java.math.BigDecimal;

public class Job {
    private final String description;
    private final BigDecimal partsCost;
    private final BigDecimal laborCost;
    private boolean completed;

    public Job(String description, BigDecimal partsCost, BigDecimal laborCost) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Опис роботи не може бути порожнім");
        }
        if (partsCost == null || partsCost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Вартість запчастин не може бути від'ємною");
        }
        if (laborCost == null || laborCost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Вартість роботи не може бути від'ємною");
        }
        this.description = description;
        this.partsCost = partsCost;
        this.laborCost = laborCost;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPartsCost() {
        return partsCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTotalCost() {
        return partsCost.add(laborCost);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Job{description='" + description + "', totalCost=" + getTotalCost() + ", completed=" + completed + "}";
    }
}