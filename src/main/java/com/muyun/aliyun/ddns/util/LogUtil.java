package com.muyun.aliyun.ddns.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.muyun.aliyun.ddns.util.GsonUtil.GSON;

/**
 * @author muyun
 * @date 2021/1/18
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogUtil {

    public static void logPrint(String functionName, Object result) {
        System.out.println("-------------------------------" + functionName + "-------------------------------");
        System.out.println(GSON.toJson(result));
    }
}
