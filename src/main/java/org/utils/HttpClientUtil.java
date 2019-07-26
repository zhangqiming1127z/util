package org.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    /**
     * 发送Get请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String info = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            info = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }


    public static String sendPost(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        CloseableHttpClient client = null;
        String result = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            if (params != null && params.size() > 0) {
                List<NameValuePair> pairList = new ArrayList<>();
                Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    pairList.add(new BasicNameValuePair(key, value));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairList, "utf-8");
                httpPost.setEntity(entity);
            }
            if (headers != null && headers.size() > 0) {
                Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    httpPost.addHeader(key, value);
                }
            }
            response = client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

}




