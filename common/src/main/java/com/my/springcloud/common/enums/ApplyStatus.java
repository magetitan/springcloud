package com.my.springcloud.common.enums;

public enum ApplyStatus {
    WAIT(0),AGREE(1),DISAGREE(2),INVALID(3);
    private int id;

    ApplyStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
