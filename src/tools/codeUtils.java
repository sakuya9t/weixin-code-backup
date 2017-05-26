package tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public final class codeUtils {
	public static boolean touchDictionary(String command){
		 try {
			   Class<?> clazz = Class.forName("code.Constants");
			   Field[] fields = clazz.getDeclaredFields();
			   for(Field f : fields) {
				   String propname = f.getType().getName();
				   if(command.equals(propname))
					   return true;
			   }
			   return false;
		 }
		 catch(Exception ex){
			ex.printStackTrace();
			return false;
		 }
	}
	
	public static String getipAddr(){
		String url = "http://1212.ip138.com/ic.asp";
		HttpClient client = new HttpClient();
		String html = null;
        PostMethod postMethod = new PostMethod(url);
        Pattern pattern;
        
        try{
        	int statusCode = client.executeMethod(postMethod);
        	System.out.println(statusCode);
        	if(statusCode != 200){
        		return "获取IP失败";
        	}
        	html = new String(postMethod.getResponseBodyAsString().getBytes("ISO-8859-1"), "gb2312");
        	pattern = Pattern.compile("<center>.+</center>");
        	Matcher m = pattern.matcher(html);
        	List<String> list = new ArrayList<String>();
        	while (m.find()){
        		String s = m.group();
        		s = s.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        		list.add(s);
        	}
        	return list.get(0);
        }
        catch(Exception ex){
        	ex.printStackTrace();
        	return "";
        }
	}

}
