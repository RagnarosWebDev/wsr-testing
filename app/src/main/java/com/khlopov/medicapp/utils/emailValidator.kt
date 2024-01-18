package com.khlopov.medicapp.utils

import java.util.Locale


fun isEmailValid(email: String): Boolean {
    if (email.lowercase(Locale.ROOT) != email) {
        return false
    }

    var split = email.split("@");

    if (split.size < 2) {
        return false
    }

    if (split.any {
            it.isEmpty()
        }) {
        return false
    }

    split = email.split(".")

    if (split.size < 2) {
        return false;
    }

    if (split.any {
            it.isEmpty()
        }) {
        return false
    }

    return true;
}