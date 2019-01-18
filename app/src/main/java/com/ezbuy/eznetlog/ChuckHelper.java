package com.ezbuy.eznetlog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ezbuy.ezutil.JsonFormatUtil;
import com.ezbuy.netlog.ChuckUtil;
import com.ezbuy.netlog.data.HttpHeader;
import com.ezbuy.netlog.data.HttpTransaction;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ChuckHelper {

    private static Gson gson = new Gson();
    private static ChuckUtil chuckUtil;
    private static List<String> filters = ListHelper.of("memoizedSerializedSize", "unknownFields", "memoizedHashCode","bitField0_");

    public static void saveModal(Context context, HttpModal modal) {
        chuckUtil = new ChuckUtil(context);
        HttpTransaction transaction = new HttpTransaction();
        transaction.setRequestDate(new Date());
        transaction.setMethod(getMethodName(modal));
        transaction.setRequestHeaders(getHttpHeaderList(modal));
        transaction.setRequestBody(getRequestBody(modal));
        transaction.setResponseBody(getResponseBody(modal));
        transaction.setUrl(getUrl(modal));
        //GRPC的url,没法自动解析url设置path，必须自己手动设置path，搜索是根据path做的
        if (modal.apiType == 0) {
            transaction.setPath(modal.methodName);
        }
        transaction.setResponseCode(200);
        chuckUtil.create(transaction);
    }

    private static String getMethodName(HttpModal modal) {
        if (modal.apiType == 0) {
            return "GRPC";
        }
        return "RPC";
    }

    @NonNull
    private static String getUrl(HttpModal modal) {
        if (modal.apiType == 0) {
            return "https://" + modal.url;
        }
        return modal.url;
    }

    private static String getResponseBody(HttpModal modal) {
        Object response = modal.response;
        return JsonFormatUtil.formatWithFilter(gson.toJson(response),filters);
    }

    private static String getRequestBody(HttpModal modal) {
        Map<String, Object> args = modal.args;
        return JsonFormatUtil.formatWithFilter(gson.toJson(args),filters);
    }

    private static List<HttpHeader> getHttpHeaderList(HttpModal modal) {
        List<HttpHeader> httpHeaders = new ArrayList<>();
        Map<String, String> header = modal.header;
        HttpHeader httpHeader = new HttpHeader("apiType", String.valueOf(modal.apiType));
        httpHeaders.add(httpHeader);
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                HttpHeader entity = new HttpHeader(entry.getKey(), entry.getValue());
                httpHeaders.add(entity);
            }
        }
        return httpHeaders;
    }

}
