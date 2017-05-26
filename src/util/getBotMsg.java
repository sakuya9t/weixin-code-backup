package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import net.sf.json.JSONObject;



public class getBotMsg {
	String url = "http://www.tuling123.com/openapi/api";
	String apikey = "0b1b42856f494b429e77320082821a09";
	public String getMsg(String inmsg){
		StringBuffer buffer = new StringBuffer();
		String param = "key=" + apikey + "&info=" + inmsg;
		String outmsg = "";
		HttpURLConnection con = null;
		try{
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection();		
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(param);
			osw.flush();
			osw.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) { 
				buffer.append(temp);
				buffer.append("\n");
				System.out.println(temp);
			}
			
			br.close();
			con.disconnect();
				
			JSONObject jsonobject = JSONObject.fromObject(buffer.toString());
			
			String msgcode = jsonobject.getString("code");
			String text = jsonobject.getString("text");
			System.out.println(msgcode);
			outmsg = text.trim();
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if (con != null) { 
				con.disconnect();
			}
		}
		
		
		return outmsg;
	}
}
