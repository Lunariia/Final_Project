package com.epam.pharmacy.model.entity;

import com.epam.pharmacy.model.Identifiable;

import java.util.Objects;

public class PurchaseStory implements Identifiable {

    private final Long id;
    private final String user;
    private final String medicine;
    private final Long amount;
    private final String dateOfPurchase;

    public PurchaseStory(Long id, String user, String medicine, Long amount, String dateOfPurchase) {
        this.id = id;
        this.user = user;
        this.medicine = medicine;
        this.amount = amount;
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getMedicine() {
        return medicine;
    }

    public Long getAmount() {
        return amount;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PurchaseStory that = (PurchaseStory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(medicine, that.medicine) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(dateOfPurchase, that.dateOfPurchase);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (medicine != null ? medicine.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (dateOfPurchase != null ? dateOfPurchase.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PurchaseStory{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", medicine='" + medicine + '\'' +
                ", amount=" + amount +
                ", dateOfPurchase='" + dateOfPurchase + '\'' +
                '}';
    }
}
