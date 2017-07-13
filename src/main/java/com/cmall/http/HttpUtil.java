package com.cmall.http;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

public class HttpUtil {
	
	private RequestConfig requestConfig = RequestConfig.custom()
		.setSocketTimeout(15000)
		.setConnectTimeout(15000)
		.setConnectionRequestTimeout(15000)
		.build();
	
	private static HttpUtil instance = null;  
    private HttpUtil(){}  
    public static HttpUtil getInstance(){  
        if (instance == null) {  
            instance = new HttpUtil();  
        }  
        return instance;
    }  
      
    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     */  
    public String sendHttpPost(String httpUrl) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        return sendHttpPost(httpPost);  
    }  
      
    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @param params 参数(格式:key1=value1&key2=value2) 
     */  
    public String sendHttpPost(String httpUrl, String params) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        try {  
            //设置参数  
            StringEntity stringEntity = new StringEntity(params, "UTF-8");  
            stringEntity.setContentType("application/x-www-form-urlencoded");  
            httpPost.setEntity(stringEntity);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sendHttpPost(httpPost);  
    }  
      
    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @param maps 参数 
     */  
    public String sendHttpPost(String httpUrl, Map<String, String> maps) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        // 创建参数队列    
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        for (String key : maps.keySet()) {  
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));  
        }  
        try {  
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sendHttpPost(httpPost);  
    }  
      
      
    /** 
     * 发送 post请求（带文件） 
     * @param httpUrl 地址 
     * @param maps 参数 
     * @param fileLists 附件 
     */  
    public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();  
        for (String key : maps.keySet()) {  
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));  
        }  
        for(File file : fileLists) {  
            FileBody fileBody = new FileBody(file);  
            meBuilder.addPart("files", fileBody);  
        }  
        HttpEntity reqEntity = meBuilder.build();  
        httpPost.setEntity(reqEntity);  
        return sendHttpPost(httpPost);  
    }  
      
    /** 
     * 发送Post请求 
     * @param httpPost 
     * @return 
     */  
    private String sendHttpPost(HttpPost httpPost) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // 创建默认的httpClient实例.  
            httpClient = HttpClients.createDefault();  
            httpPost.setConfig(requestConfig);  
            // 执行请求  
            response = httpClient.execute(httpPost);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
      

    /**
     * 
     * @param url
     * @return
     */
    public String sendHttpGet(String url) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity responseEntity = null;  
        HttpGet httpGet = null;
        String responseContent = null;  
        try {  
            // 创建默认的httpClient实例.  
            httpClient = HttpClients.createDefault();  
            httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);  
            // 执行请求  
            response = httpClient.execute(httpGet);  
            responseEntity = response.getEntity();  
            // Get the entity content as a String
            responseContent = EntityUtils.toString(responseEntity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
      

    /**
     * 
     * @param url
     * @return
     */
    public String sendHttpsGet(String url) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        HttpGet httpGet = null;
        String responseContent = null;  
        try {  
        	httpGet = new HttpGet(url);
            // 创建默认的httpClient实例.  
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));  
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);  
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();  
            httpGet.setConfig(requestConfig);  
            // 执行请求  
            response = httpClient.execute(httpGet);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }
    
    
    @Test  
    public void testSendHttpPost1() {  
        String responseContent = HttpUtil.getInstance()  
                .sendHttpPost("http://localhost:8089/test/send?username=test01&password=123456");  
        System.out.println("reponse content:" + responseContent);  
    }  
      
    @Test  
    public void testSendHttpPost2() {  
        String responseContent = HttpUtil.getInstance()  
                .sendHttpPost("http://localhost:8089/test/send", "username=test01&password=123456");  
        System.out.println("reponse content:" + responseContent);  
    }  
      
    @Test  
    public void testSendHttpPost3() {  
        Map<String, String> maps = new HashMap<String, String>();  
        maps.put("username", "test01");  
        maps.put("password", "123456");  
        String responseContent = HttpUtil.getInstance()  
                .sendHttpPost("http://localhost:8089/test/send", maps);  
        System.out.println("reponse content:" + responseContent);  
    }  
    @Test  
    public void testSendHttpPost4() {  
        Map<String, String> maps = new HashMap<String, String>();  
        maps.put("username", "test01");  
        maps.put("password", "123456");  
        List<File> fileLists = new ArrayList<File>();  
        fileLists.add(new File("D://test//httpclient//1.png"));  
        fileLists.add(new File("D://test//httpclient//1.txt"));  
        String responseContent = HttpUtil.getInstance()  
                .sendHttpPost("http://localhost:8089/test/sendpost/file", maps, fileLists);  
        System.out.println("reponse content:" + responseContent);  
    }  
  
    @Test  
    public void testSendHttpGet() {  
        String responseContent = HttpUtil.getInstance()  
                .sendHttpGet("http://localhost:8089/test/send?username=test01&password=123456");  
        System.out.println("reponse content:" + responseContent);  
    }  
      
    @Test  
    public void testSendHttpsGet() {  
        String responseContent = HttpUtil.getInstance()  
                .sendHttpsGet("https://www.baidu.com");  
        System.out.println("reponse content:" + responseContent);  
    }  

}
