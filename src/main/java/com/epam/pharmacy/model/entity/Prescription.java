package com.epam.pharmacy.model.entity;

import com.epam.pharmacy.model.Identifiable;

import java.util.Objects;

public class Prescription implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String user;
    private final String medicine;
    private final String startDate;
    private final String endDate;
    private final Boolean access;

    public Prescription(Long id, String user, String medicine, String startDate, String endDate, Boolean access) {
        this.id = id;
        this.user = user;
        this.medicine = medicine;
        this.startDate = startDate;
        this.endDate = endDate;
        this.access = access;
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Boolean getAccess() {
        return access;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Prescription that = (Prescription) o;
        return id.equals(that.id) &&
                user.equals(that.user) &&
                medicine.equals(that.medicine) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                Objects.equals(access, that.access);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (medicine != null ? medicine.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (access != null ? access.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", medicine='" + medicine + '\'' +
                ", start_date='" + startDate + '\'' +
                ", end_date='" + endDate + '\'' +
                ", access=" + access +
                '}';
    }
}
