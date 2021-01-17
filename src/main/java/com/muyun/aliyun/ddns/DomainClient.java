package com.muyun.aliyun.ddns;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.muyun.aliyun.ddns.properties.AliyunProperties;
import lombok.RequiredArgsConstructor;

/**
 * 动态域名解析
 */
@RequiredArgsConstructor
public class DomainClient {

    private final IAcsClient client;

    public static DomainClient from(AliyunProperties properties) {
        // 设置鉴权参数，初始化客户端
        DefaultProfile profile = DefaultProfile.getProfile(
                properties.getRegionId(),// 地域ID
                properties.getAccessKeyId(),// 您的AccessKey ID
                properties.getSecret());// 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        return new DomainClient(client);
    }

    /**
     * 获取主域名的所有解析记录列表
     */
    public DescribeDomainRecordsResponse describeDomainRecords(DescribeDomainRecordsRequest request) {
        try {
            // 调用SDK发送请求
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            // 发生调用错误，抛出运行时异常
            throw new RuntimeException();
        }
    }

    /**
     * 修改解析记录
     */
    public UpdateDomainRecordResponse updateDomainRecord(UpdateDomainRecordRequest request) {
        try {
            // 调用SDK发送请求
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            // 发生调用错误，抛出运行时异常
            throw new RuntimeException();
        }
    }

}