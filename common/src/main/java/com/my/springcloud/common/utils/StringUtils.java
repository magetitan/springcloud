package com.my.springcloud.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils extends org.apache.commons.lang3.StringUtils{
    public static void main(String[] args) {
        String str = "21072701452706F00,-700E一绞车房-700mE1绞车房;;;";
        String[] strs1 = str.split(",");
        System.out.println(strs1.length);
        String[] strs2 = org.apache.commons.lang3.StringUtils.split(str, split);
        System.out.println(strs2.length);
    }
    private static final Logger log = LoggerFactory.getLogger(String.class);
    static final String split = ";";
    static final String lineSplit=System.lineSeparator();
    
    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
    public static String[] lineSplit(String str) {
        return str.split(lineSplit);
    }

    public static String[] split(String split,String str) {
        return str.trim().split(split);
    }
    
    public static String[] split(String str) {
        return split(split, str);
    }
    
    
    public static String acquire(String[] strs, int index) {
        return acquire(strs, index , true) ;
    }
    
    public static String acquire(String[] strs, int index , boolean catchException) {
        String str = null;
        if(catchException) {
            try {
                str = strs[index];
            } catch (Exception e) {
                log.debug("StringUtils.acquire: ",e);
            }
        }else {
            str = strs[index];
        }
        return str;
    }
}
