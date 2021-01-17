package com.muyun.aliyun.ddns.util;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author muyun
 * @date 2021/1/17
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GsonUtil {

    public static final Gson GSON = new Gson();

}
