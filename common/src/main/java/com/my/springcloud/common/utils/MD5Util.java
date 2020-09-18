package com.my.springcloud.common.utils;

import org.springframework.util.DigestUtils;

/**
 * MD5工具类
 *
 */
public class MD5Util {
    //盐，用于混交md5
    private static final String slat = "&%93248***&&%%$$#@";
    /**
     * 生成md5
     * @return
     */
    public static String getMD5(String str) {
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    public static void main(String[] args) {
        String md5 = MD5Util.getMD5("jdlmk");
        System.out.println(md5);
    }

}
