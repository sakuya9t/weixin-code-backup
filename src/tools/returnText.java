package tools;

import java.util.Date;

public class returnText {
	public static String responsebyText(String content, String fromUsername, String toUsername){
        String textTpl = "<xml>"+  
                "<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
                "<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
                "<CreateTime>%3$s</CreateTime>"+  
                "<MsgType><![CDATA[%4$s]]></MsgType>"+  
                "<Content><![CDATA[%5$s]]></Content>"+  
                "<FuncFlag>0</FuncFlag>"+  
                "</xml>";  
		String msgType = "text";  
		String time = new Date().getTime()+"";  
        return String.format(textTpl, toUsername, fromUsername, time, msgType, content); 
	}
}
