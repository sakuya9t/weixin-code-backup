<%@page import="java.io.*"%>
<%@page import="java.net.*" %>
<%@page import="org.json.*" %>
<%@page import="tools.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
class MenuGetPost{
	
	public int createMenu() throws IOException {
		String user_define_menu = "{\"button\":[{\"type\":\"click\",\"name\":\"打开页面\",\"key\":\"20_OPENWEBPAGE\"},{\"type\":\"click\",\"name\":\"回复消息\",\"key\":\"30_REPLYMESSAGE\"},{\"name\":\"子目录\",\"sub_button\":[{\"type\":\"click\",\"name\":\"item1\",\"key\":\"01_item\"},{\"type\":\"click\",\"name\":\"item2\",\"key\":\"02_item\"}]}]}";
		AccessToken token = new AccessToken();
		String access_token= token.getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
		try{
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			
			http.setRequestMethod("POST");        
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
			http.setDoOutput(true);        
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
			
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(user_define_menu.getBytes("UTF-8"));//传入参数
			os.flush();
			os.close();
			
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes,"UTF-8");
			System.out.println(message);
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}
}
%>
<%
MenuGetPost mgp = new MenuGetPost();
mgp.createMenu();
%>