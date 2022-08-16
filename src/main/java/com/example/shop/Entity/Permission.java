package com.example.shop.Entity;

public enum Permission {
    BUY("BUY"),SELL("SELL"),ADMIN("ADMIN");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
