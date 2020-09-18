package com.my.springcloud.common.utils;

import org.slf4j.Logger;

public class LogUtil {
    public static void error(Throwable throwable, String message, Logger logger){
        logger.error(message,throwable);
    }
}
