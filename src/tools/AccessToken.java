package tools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class AccessToken {
	String appId = "wxe4b823ea225c71ed";   
	String appSecret = "c05f3e5ad90bf0dcefe4d8a1f9a6aeb5"; 
	public String getAccess_token(){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId + "&secret=" +appSecret;
		String accessToken = null;
		try{
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			
			http.connect();
			
			InputStream is = http.getInputStream();
			int size =is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes,"UTF-8");

			JSONObject demoJson = new JSONObject(message);
			accessToken = demoJson.getString("access_token");

			System.out.println(message);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
}
