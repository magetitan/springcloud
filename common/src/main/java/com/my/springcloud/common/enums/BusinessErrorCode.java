package com.my.springcloud.common.enums;


public enum BusinessErrorCode  {

    /**
     * 10 元数据采集模块 1000 COLLECT
     */

    DATA_COLLECT_1000001(1000001, "账号或密码错误"),
    DATA_COLLECT_1000002(1000002, "账号禁用"),
    DATA_COLLECT_1000003(1000003, "该用户不存在"),

    /**
     * 10 元数据管理模块 1010  MANAGE
     */
    DATA_MANAGE_1010001(1010001, "数据校验失败"),
    DATA_MANAGE_1010002(1010002, "入参输入错误"),
    DATA_MANAGE_1010003(1010003, "新增失败"),
    DATA_MANAGE_1010004(1010004, "修改失败"),
    DATA_MANAGE_1010005(1010005, "删除失败"),
    DATA_MANAGE_1010006(1010006, "类型不能为空"),

    /**
     * 数据资产模块 1011  ASSET
     */

    /**
     * 数据标准模块 1012 standard STANDARD
     */


    /**
     * 数据质量模块 1013  QUALITY
     */
    DATA_QUALITY_1013001(1013001, "添加失败"),
    /**
     * 数据安全模块 1014  SECURITY
     */
    DATA_SECURITY_1114001(1114001, "失败");



    private int code;
    private String msg;

    BusinessErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BusinessErrorCode getEnum(int index) {
        for (BusinessErrorCode c : BusinessErrorCode.values()) {
            if (c.value() == index) {
                return c;
            }
        }
        return null;
    }

    public int value() {
        return this.code;
    }

    public String desc() {
        return this.msg;
    }
}
