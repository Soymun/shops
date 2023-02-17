package com.example.shop.Entity;

public enum Status {

    CREATE("CREATE"), PAY("PAY"), COLLECT("COLLECT"), READY("READY");

    final String p;

    Status(String p) {
        this.p = p;
    }
}
