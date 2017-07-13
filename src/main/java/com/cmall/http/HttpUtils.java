package com.cmall.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 准备扩展该类
 * @author cm
 *
 */
public class HttpUtils {
	
	static CookieStore cookieStore=null;
	static CloseableHttpClient httpclient=HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(15000)
			.setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000)
			.build();
	
	/**
	 * 将 String 转换成JsonObject 
	 * @param str
	 * @return
	 */
	public static JsonObject getJson(String str){
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(str);
		return jsonObject;
	}
	
	/**
	 * 发送Http get请求，如果url已经携带参数，则指定paramsMap为null，否则需要指定paramsMap参数
	 * @param url
	 * @param paramsMap
	 * @return
	 */
    public static String sendHttpGet(String url,Map<String, String> paramsMap) {  
    	
        CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建默认的httpClient实例.  
        CloseableHttpResponse response = null;  
        HttpGet httpGet = null;
        String responseStr = null;  
		UrlEncodedFormEntity entitys = getFormEntity(paramsMap);
        try {  
            if (entitys != null) {
                httpGet = new HttpGet(url + '?' + EntityUtils.toString(entitys));
            } else {
            	httpGet = new HttpGet(url);
            }
            httpGet.setConfig(requestConfig);  
            // 执行请求  
            response = httpClient.execute(httpGet);  
            HttpEntity responseEntity = response.getEntity();  
            // Get the entity content as a String
            responseStr = EntityUtils.toString(responseEntity, "UTF-8");  

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
        return responseStr;  
    } 
    
    /**
     * 参考这个
     * @param url
     * @param paramsMap
     * @param httpclient
     * @param cookieStore
     * @return
     */
	public static CloseableHttpResponse doGet(String url, Map<String, String> paramsMap, CloseableHttpClient httpclient,
			CookieStore cookieStore) {

		HttpGet httpget = null;
		UrlEncodedFormEntity entitys = getFormEntity(paramsMap);

		try {
			httpget = new HttpGet(url + '?' + EntityUtils.toString(entitys));
			if (cookieStore != null) {
				httpget.setHeader("Cookie", "JSESSIONID=" + cookieStore.getCookies().get(0).getValue().trim());
			}

			// 执行get请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
			CloseableHttpResponse httpResponse = httpclient.execute(httpget);
			return httpResponse;

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    /**
     * An entity composed of a list of url-encoded pairs. This is typically useful while sending an HTTP POST request.
     * 
     * @param paramsMap
     * @return 如果paramsMap 为null，则返回null
     */
	public static UrlEncodedFormEntity getFormEntity(Map<String, String> paramsMap) {
		UrlEncodedFormEntity entitys = null;
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		if (paramsMap != null) {
			Set<String> keySet = paramsMap.keySet();
			for (String key : keySet) {
				paramsList.add(new BasicNameValuePair(key, paramsMap.get(key)));
			}
			entitys = new UrlEncodedFormEntity(paramsList, Consts.UTF_8);
		}
		return entitys;
	}
	
	
	@Test
	public void test01() {
		String url = "http://android.cmall.com/goodsSite/home/goodsList";
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("productId", "501");
		String response = sendHttpGet(url,paramsMap);
		JsonObject json = getJson(response);
		System.out.println(json);

	}

}
