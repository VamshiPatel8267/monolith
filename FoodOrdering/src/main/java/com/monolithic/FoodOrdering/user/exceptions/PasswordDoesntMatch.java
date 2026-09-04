package com.monolithic.FoodOrdering.user.exceptions;

public class PasswordDoesntMatch extends RuntimeException {
    public PasswordDoesntMatch(String message) {
        super(message);
    }


}
