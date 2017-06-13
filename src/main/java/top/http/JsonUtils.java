package top.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class JsonUtils {
	
	static CookieStore cookieStore=null;

	static CloseableHttpClient httpclient=HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	
	public void test(){
		
		String url = "http://m.cmall.com/gdCategorySite/category/getHomeCategory";
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("clientType", "H5");
		paramsMap.put("abbr", "CN");
		paramsMap.put("page", "index");
		
		CloseableHttpResponse httpResponse = HttpClientUtil.doGet(url, paramsMap, httpclient, cookieStore);
		
		ResponseBean responseBean = ReponseUtil.setResponseBean(httpResponse);
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(responseBean.getBody());
		
		JsonArray result = (JsonArray) jsonObject.get("result").getAsJsonArray();
		System.out.println(result.size());
		
		JsonObject subResult = result.get(0).getAsJsonObject();
		System.out.println(subResult.get("name"));
		


	}
	
	public static JsonObject getJsonBydoGet(String url,Map<String, String> paramsMap){

		CloseableHttpResponse httpResponse = HttpClientUtil.doGet(url, paramsMap, httpclient, cookieStore);
		ResponseBean responseBean = ReponseUtil.setResponseBean(httpResponse);
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(responseBean.getBody());
		return jsonObject;
	}
	
	public static void main(String[] args) {

		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("productId", "501");
		JsonObject jsonObject = getJsonBydoGet("http://android.cmall.com/goodsSite/home/goodsList",paramsMap);
		JsonObject result = (JsonObject) jsonObject.get("result");
		JsonArray pageItems = result.get("pageItems").getAsJsonArray();
		System.out.println(pageItems.size());
	}

}
