package com.banking.app.util;

public class InputValidation {

    public static boolean isAnIntegerAndGreaterThanZero(String userInput) {
        try {
            int num = Integer.parseInt(userInput);
            return num >= 0;
        } catch (Exception e) {
            System.out.println("Must enter numeric input.");
            return false;
        }
    }

}
