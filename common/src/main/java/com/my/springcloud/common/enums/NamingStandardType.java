package com.my.springcloud.common.enums;

public enum NamingStandardType {
    DATABASE(0),TABLE(1);

    private int id;
    NamingStandardType(int i) {
        this.id=i;
    }

    public int getId() {
        return id;
    }
}
