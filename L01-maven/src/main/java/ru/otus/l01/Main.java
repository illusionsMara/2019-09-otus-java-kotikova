package ru.otus.l01;

public class Main {
    public static void main(String[] args) {
        String email = "client@mail.ru";
        String phone = "7(777)777-77-77";

        String clientContact = HelloOtus.getClientContact(email, phone);
        System.out.println("clientContact: " + clientContact);
    }
}
