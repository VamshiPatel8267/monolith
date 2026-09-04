package com.monolithic.FoodOrdering.user.exceptions;

public class UserDoesnotExists extends RuntimeException {
    public UserDoesnotExists(String message) {
        super(message);
    }
}
