package com.my.springcloud.common.utils;

public class IntegerUtil {
    public static boolean equalsInt(Integer a,Integer b) {
        boolean n = false;
        if(a == b) {
            n =true;
        }else if(a !=null && b != null) {
            n = (a.intValue() == b.intValue() );
        }
        return n;
    }
    public static Integer parseInt(String str) {
        return str==null?null:Integer.parseInt(str);
    }
}
