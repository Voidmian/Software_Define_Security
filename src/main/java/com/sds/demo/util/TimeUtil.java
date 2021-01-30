package com.sds.demo.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Author Voidmian
 * @Date 2021/1/28 15:38
 */
public class TimeUtil {
    static public LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of("GMT+16:00"));
    }
}
