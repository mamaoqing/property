package com.shige.proper.controller;

import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author mq
 * @date 2020/7/28 13:51
 * @description controller的父类
 */
@Controller("testBaseController")
@Slf4j
public class BaseController {



    /**
     * 将请求参数转map
     * @param request 参数
     */
    protected Map<String, String> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<String, String[]> properties = request.getParameterMap();
        // 返回值Map
        Map<String, String> returnMap = new HashMap<String, String>(16);
        Iterator entries = properties.entrySet().iterator();
        Map.Entry<String, Object> entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 微服务间调用 get
     * @param restTemplate 微服务调用
     * @param url 请求地址
     * @param object 参数
     */
    protected Result doGetRestTemplate(RestTemplate restTemplate, String url, Object object){
        StringBuilder stringBuffer = new StringBuilder(url);
        if (object instanceof Map) {
            Iterator iterator = ((Map) object).entrySet().iterator();
            if (iterator.hasNext()) {
                stringBuffer.append("?");
                Object element;
                while (iterator.hasNext()) {
                    element = iterator.next();
                    Map.Entry<String, Object> entry = (Map.Entry) element;
                    //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                    if (entry.getValue() != null) {
                        stringBuffer.append(element).append("&");
                    }
                    url = stringBuffer.substring(0, stringBuffer.length() - 1);
                }
            }
        } else {
            throw new RuntimeException("url请求:" + url + "请求参数有误不是map类型");
        }
        log.info("url请求:" + url);
        return restTemplate.getForObject(url, Result.class);
    }

    /**
     * 统一get请求
     * @param restTemplate 请求
     * @param url 请求的连接
     * @param request 请求参数
     * @param token 用户登录唯一凭证
     * @return 返回一个实体对象
     */
    protected Result doGetRestTemplate(RestTemplate restTemplate, String url, HttpServletRequest request, String token){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(ShigeConstant.TOKEN,token);
        StringBuilder stringBuffer = new StringBuilder(url);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", requestHeaders);
        Map<String, String> map = this.getParameterMap(request);
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();
            if (iterator.hasNext()) {
                stringBuffer.append("?");
                Object element;
                while (iterator.hasNext()) {
                    element = iterator.next();
                    Map.Entry<String, Object> entry = (Map.Entry) element;
                    //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                    if (entry.getKey() != null) {
                        stringBuffer.append(entry.getKey()).append("=").append("{").append(entry.getKey()).append("}").append("&");
                    }
                    url = stringBuffer.substring(0, stringBuffer.length() - 1);
                }
            }
        } else {
            throw new RuntimeException("url请求:" + url + "请求参数有误不是map类型");
        }
        log.info("url请求:" + url);
        if(map.isEmpty()){
            return restTemplate.exchange(url, HttpMethod.GET,requestEntity,Result.class).getBody();
        }
        return restTemplate.exchange(url, HttpMethod.GET,requestEntity,Result.class,map).getBody();
    }

    protected Result doGetRestTemplate(String url, HttpServletRequest request, String token){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(ShigeConstant.TOKEN,token);
        StringBuilder stringBuffer = new StringBuilder(url);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", requestHeaders);
        Map<String, String> map = this.getParameterMap(request);
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();
            if (iterator.hasNext()) {
                stringBuffer.append("?");
                Object element;
                while (iterator.hasNext()) {
                    element = iterator.next();
                    Map.Entry<String, Object> entry = (Map.Entry) element;
                    //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                    if (entry.getKey() != null) {
                        stringBuffer.append(entry.getKey()).append("=").append("{").append(entry.getKey()).append("}").append("&");
                    }
                    url = stringBuffer.substring(0, stringBuffer.length() - 1);
                }
            }
        } else {
            throw new RuntimeException("url请求:" + url + "请求参数有误不是map类型");
        }
        log.info("url请求:" + url);
        if(map.isEmpty()){
            return new RestTemplate().exchange(url, HttpMethod.GET,requestEntity,Result.class).getBody();
        }
        return new RestTemplate().exchange(url, HttpMethod.GET,requestEntity,Result.class,map).getBody();
    }

    /**
     * post 请求
     * @param restTemplate restTemplate
     * @param url 请求地址
     * @param request 对象内容
     * @param token 用户登录凭证
     */
    protected Result doPostRestTemplate(RestTemplate restTemplate, String url, HttpServletRequest request, String token, HttpMethod method){
        Map<String, String> parameterMap = getParameterMap(request);
        HttpHeaders headers = new HttpHeaders();
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(ShigeConstant.TOKEN,token);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(this.getParams(request), headers);
        return restTemplate.exchange(url,method,requestEntity,Result.class).getBody();
    }


    protected Result doPostObject(RestTemplate restTemplate,String url, Object object, String token, HttpMethod method){
        HttpHeaders headers = new HttpHeaders();
        // 以表单的方式提交
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(ShigeConstant.TOKEN,token);
        //将请求头部和参数合成一个请求
        HttpEntity requestEntity = new HttpEntity<>(object, headers);
        return restTemplate.exchange(url,method,requestEntity,Result.class).getBody();
    }


    public MultiValueMap getParams(HttpServletRequest request) {
        MultiValueMap ret = new LinkedMultiValueMap();
        Map<String,String[]> map = request.getParameterMap();
        for(Iterator<Map.Entry<String, String[]>> itr = map.entrySet().iterator(); itr.hasNext();){
            Map.Entry<String, String[]> entry = itr.next();
            String key = entry.getKey();
            String[] value = entry.getValue();
            ret.put(key, new ArrayList(Arrays.asList(value[0])));
        }
        // 提取params参数
        return ret;
    }
}
