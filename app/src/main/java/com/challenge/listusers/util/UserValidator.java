package com.challenge.listusers.util;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static boolean isValidName(String name) {
        return name != null && name.length() > 30;
    }

    public static boolean isValidPassword(String password) {

        String regex = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
