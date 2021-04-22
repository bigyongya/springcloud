package com.h4kit.zuul.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyFallback  implements FallbackProvider {

    // json处理工具
    private ObjectMapper mapper = new ObjectMapper();
 
    //针对哪一个路由进行降级， return 可以写*
    @Override
    public String getRoute() {
        return "*";
    }
 
    //降级时处理方式
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }
 
            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }
 
            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }
 
            @Override
            public void close() {
 
            }
 
            //业务降级处理方式
            @Override
            public InputStream getBody() throws IOException {
//                BaseResponseVO responseVO =  BaseResponseVO.serviceException(
//                        new CommonServiceException(404,"System error!~"));

                Map<String,String> map = new HashMap<>();
                map.put("code","404");
                map.put("message","System error!~");

//                String result = JSONObject.toJSONString(responseVO);
                // 序列化
                String json = mapper.writeValueAsString(map);
                return new ByteArrayInputStream(json.getBytes());
            }
 
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}