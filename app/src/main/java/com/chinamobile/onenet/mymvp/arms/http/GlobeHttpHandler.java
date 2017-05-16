package com.chinamobile.onenet.mymvp.arms.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
* @Name: GlobeHttpHandler
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 21:55
*/

public interface GlobeHttpHandler {
    Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response);

    Request onHttpRequestBefore(Interceptor.Chain chain, Request request);

    GlobeHttpHandler EMPTY = new GlobeHttpHandler() {
        @Override
        public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
            //不管是否处理,都必须将response返回出去
            return response;
        }

        @Override
        public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
            //不管是否处理,都必须将request返回出去
            return request;
        }
    };

}
