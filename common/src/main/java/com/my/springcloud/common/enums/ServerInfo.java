package com.my.springcloud.common.enums;

public enum ServerInfo {
    HOST_IP_ADDRESS("47.92.240.63"),
    HOST_PORT("7071"),
    DATA_ASSET("data-asset");

    private String code;
    ServerInfo(String s) {
        this.code=s;
    }

    public String getCode() {
        return code;
    }
}
