package com.chinaliyq.eurekaconsumer;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @Author: liyq
 * @Description: eureka-sercer
 * @Date: 2021/4/24 - 17:10
 * @Version: 1.0
 **/
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        System.out.println("拦截啦！！！");
        System.out.println("访问地址：" + request.getURI());

        ClientHttpResponse response = execution.execute(request, body);

        System.out.println("返回值：" + response.getHeaders());
        return response;
    }
}