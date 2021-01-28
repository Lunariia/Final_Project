package com.epam.pharmacy.model.entity;


import com.epam.pharmacy.model.Identifiable;

import java.util.Objects;

public final class Medicine implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String title;
    private final Double dosage;
    private final Double cost;
    private final Boolean prescription;
    private final Long quantity;

    public Medicine(Long id, String title, Double dosage, Double cost, Boolean prescription, Long quantity) {
        this.id = id;
        this.title = title;
        this.dosage = dosage;
        this.cost = cost;
        this.prescription = prescription;
        this.quantity = quantity;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getDosage() {
        return dosage;
    }

    public Double getCost() {
        return cost;
    }

    public Boolean getPrescription() {
        return prescription;
    }

    public Long getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Medicine medicine = (Medicine) o;
        return Objects.equals(id, medicine.id) &&
                Objects.equals(title, medicine.title) &&
                Objects.equals(dosage, medicine.dosage) &&
                Objects.equals(cost, medicine.cost) &&
                Objects.equals(prescription, medicine.prescription) &&
                Objects.equals(quantity, medicine.quantity);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (prescription != null ? prescription.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dosage=" + dosage +
                ", cost=" + cost +
                ", prescription=" + prescription +
                ", quantity=" + quantity +
                '}';
    }

}
