package ru.otus.l01;

import static com.google.common.base.Strings.isNullOrEmpty;

public class HelloOtus {

    public static String getClientContact(String email, String phone) {
        if(isNullOrEmpty(email)) {
            if (isNullOrEmpty(phone)) {
                return "This client has no contact details.";
            } else {
                return phone;
            }
        }
        return email;
    }
}
