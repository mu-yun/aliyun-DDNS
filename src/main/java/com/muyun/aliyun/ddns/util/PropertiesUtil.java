package com.muyun.aliyun.ddns.util;

import com.muyun.aliyun.ddns.properties.AliyunProperties;
import com.muyun.aliyun.ddns.properties.DomainProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Properties;

import static com.muyun.aliyun.ddns.util.GsonUtil.GSON;

/**
 * @author muyun
 * @date 2021/1/17
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesUtil {
    private static final String ALIYUN_PROPERTIES = "aliyun.properties";
    private static final String DOMAIN_PROPERTIES = "domain.properties";

    public static AliyunProperties getAliyunProperties() {
        return getProperties(ALIYUN_PROPERTIES, AliyunProperties.class);
    }

    public static DomainProperties getDomainProperties() {
        return getProperties(DOMAIN_PROPERTIES, DomainProperties.class);
    }

    private static <T> T getProperties(String propertiesName, Class<T> propertiesClass) {
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemResourceAsStream(propertiesName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加载配置失败:" + propertiesName);
        }
        return GSON.fromJson(properties.toString(), propertiesClass);
    }
}
