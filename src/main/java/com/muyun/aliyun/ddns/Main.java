package com.muyun.aliyun.ddns;

import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.muyun.aliyun.ddns.properties.AliyunProperties;
import com.muyun.aliyun.ddns.properties.DomainProperties;
import com.muyun.aliyun.ddns.util.IPUtil;
import com.muyun.aliyun.ddns.util.LogUtil;
import com.muyun.aliyun.ddns.util.PropertiesUtil;

import java.util.List;

/**
 * @author muyun
 * @date 2021/1/17
 */
public class Main {

    public static void main(String[] args) {
        AliyunProperties aliyunProperties = PropertiesUtil.getAliyunProperties();
        DomainClient domainClient = DomainClient.from(aliyunProperties);

        DomainProperties domainProperties = PropertiesUtil.getDomainProperties();

        DescribeDomainRecordsRequest getRequest = getDescribeDomainRecordsRequest(domainProperties);
        DescribeDomainRecordsResponse describeDomainRecordsResponse = domainClient.describeDomainRecords(getRequest);
        LogUtil.logPrint("describeDomainRecords", describeDomainRecordsResponse);

        List<DescribeDomainRecordsResponse.Record> domainRecords = describeDomainRecordsResponse.getDomainRecords();
        // 最新的一条解析记录
        if (domainRecords.size() != 0) {
            DescribeDomainRecordsResponse.Record record = domainRecords.get(0);
            // 记录ID
            String recordId = record.getRecordId();
            // 记录值
            String recordsValue = record.getValue();
            // 当前主机公网IP
            String currentHostIP = IPUtil.getCurrentHostIP();
            System.out.println("-------------------------------当前主机公网IP为：" + currentHostIP +
                    "-------------------------------");
            if (!currentHostIP.equals(recordsValue)) {
                UpdateDomainRecordRequest updateRequest = getUpdateDomainRecordRequest(domainProperties,
                        recordId, currentHostIP);
                UpdateDomainRecordResponse updateDomainRecordResponse = domainClient.updateDomainRecord(updateRequest);
                LogUtil.logPrint("updateDomainRecord", updateDomainRecordResponse);
            }
        }
    }

    private static UpdateDomainRecordRequest getUpdateDomainRecordRequest(DomainProperties domainProperties,
                                                                          String recordId, String currentHostIP) {
        // 修改解析记录
        UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest();
        // 记录ID
        updateDomainRecordRequest.setRecordId(recordId);
        // 主机记录
        updateDomainRecordRequest.setRR(domainProperties.getRRKeyWord());
        // 将主机记录值改为当前主机IP
        updateDomainRecordRequest.setValue(currentHostIP);
        // 解析记录类型
        updateDomainRecordRequest.setType(domainProperties.getType());
        return updateDomainRecordRequest;
    }

    private static DescribeDomainRecordsRequest getDescribeDomainRecordsRequest(DomainProperties domainProperties) {
        DescribeDomainRecordsRequest describeDomainRecordsRequest = new DescribeDomainRecordsRequest();
        // 主域名
        describeDomainRecordsRequest.setDomainName(domainProperties.getDomainName());
        // 主机记录
        describeDomainRecordsRequest.setRRKeyWord(domainProperties.getRRKeyWord());
        // 解析记录类型
        describeDomainRecordsRequest.setType(domainProperties.getType());
        return describeDomainRecordsRequest;
    }
}
