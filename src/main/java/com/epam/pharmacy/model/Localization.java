package com.epam.pharmacy.model;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Localization {

    EN(ResourceBundle.getBundle("property.locale", Locale.ENGLISH)),
    BE(ResourceBundle.getBundle("property.locale", new Locale("by", "BY"))),
    RU(ResourceBundle.getBundle("property.locale", new Locale("ru", "RU")));

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
