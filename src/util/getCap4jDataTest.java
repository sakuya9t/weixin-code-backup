package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class getCap4jDataTest {
	private String linkTest(String url, Map<String, String> params){
		HttpURLConnection con = null;
		String s = "";
		
		if (params != null) { 
			for (Entry<String, String> e : params.entrySet()) {
				s += e.getKey();
				s += "="; 
				s += e.getValue();
				s += "&";
			}
			s = s.substring(0, s.length() - 1);
		}
		System.out.println("send_url:" + url);
		System.out.println("send_data:" + s);
		try {
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(s);
			osw.flush();
			osw.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if (con != null) { 
				con.disconnect();
			}
		}
		
		
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) { 
				buffer.append(temp);
				buffer.append("\n");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return buffer.toString();
	}
	
	public String test4(){
		Map<String, String> parames = new HashMap<String, String>();
		parames.put("param1", "param1_value");
		parames.put("param2", "param2_value");
		parames.put("param3", "param3_value");
		return this.linkTest("http://localhost:8999/test4/wxtest", parames);
	}
}
