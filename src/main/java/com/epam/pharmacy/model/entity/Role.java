package com.epam.pharmacy.model.entity;

import com.epam.pharmacy.constants.CommandName;

import java.util.Arrays;
import java.util.List;

public enum Role {
    GUEST(
            Arrays.asList(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGIN,
                    CommandName.LOGIN_PAGE,
                    CommandName.ABOUT_US_PAGE,
                    CommandName.CONTACTS_PAGE,
                    CommandName.DEPARTMENTS_PAGE
            )
    ),
    CUSTOMER(
            Arrays.asList(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGIN,
                    CommandName.LOGIN_PAGE
            )
    ),
    DOCTOR(
            Arrays.asList(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGOUT,
                    CommandName.ACCEPT_PRESCRIPTION,
                    CommandName.REFUSE_PRESCRIPTION
            )
    ),
    WORKER(
            Arrays.asList(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGOUT,
                    CommandName.CREATE_MEDICINE,
                    CommandName.DELETE_MEDICINE
            )
    );

    private final List<String> permissions;

    Role(List<String> permissions) {
        this.permissions = permissions;
    }

    public boolean hasAccess(String name) {
        return permissions.contains(name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "permissions=" + permissions +
                '}';
    }
}
