package com.epam.pharmacy.model;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Localization {

    EN(ResourceBundle.getBundle("property.localization", Locale.ENGLISH)),
    BE(ResourceBundle.getBundle("property.localization", new Locale("by", "BY"))),
    RU(ResourceBundle.getBundle("property.localization", new Locale("ru", "RU")));

    private final ResourceBundle bundle;

    Localization(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public Locale getLocale() {
        return bundle.getLocale();
    }

    public String getBaseBundleName() {
        return bundle.getBaseBundleName();
    }
}
