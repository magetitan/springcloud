package com.my.springcloud.common.enums;

public enum DataAssetType {
    PROJECT(1),DATABASE(2),TABLE(3);

    private int id;
    DataAssetType(int i) {
        this.id=i;
    }
    public int getId(){
        return this.id;
    }
}
