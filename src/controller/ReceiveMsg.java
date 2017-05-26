/**
  * �ļ�˵��
  * @Description:��չ˵��
  * @Copyright: 2015 dreamtech.com.cn Inc. All right reserved
  * @Version: V6.0
  */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.dom4j.Element;
import org.dom4j.DocumentHelper;
import org.dom4j.Document; 

import bean.Code;

import tools.returnText;
import util.JDBCUtil;
import util.getBotMsg;

//ͨ自动回复controller
@Controller
public class ReceiveMsg {
	static String TOKEN = "999999";  
	static HttpServletRequest final_request;   
	static HttpServletResponse final_response;  
	
	@RequestMapping(value="/receive")
	@ResponseBody
	public void valid(HttpServletRequest request, HttpServletResponse response){
		final_request = request;
		final_response = response;
		final_response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		 String echostr=request.getParameter("echostr");  
	        if(null==echostr||echostr.isEmpty()){  
	            responseMsg();  
	        }else{  
	            if(this.checkSignature(request, response)){  
	                this.print(echostr);  
	            }else{  
	                this.print("error");                                                                                                                                                                                                                                                                                                                                           
	            }  
	        }  
		
	}

	//自动回复内容  
    public void responseMsg(){  
        String postStr=null;  
        try{  
            postStr=this.readStreamParameter(final_request.getInputStream());  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        //System.out.println(postStr);  
        if (null!=postStr&&!postStr.isEmpty()){  
            Document document=null;  
            try{  
                document = DocumentHelper.parseText(postStr);  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
            if(null==document){  
                this.print("");  
                return;  
            }  
            Element root=document.getRootElement();  
            String MsgType = root.elementTextTrim("MsgType");
            String fromUsername = root.elementText("FromUserName");  
            String toUsername = root.elementText("ToUserName");  
            String keyword = root.elementTextTrim("Content");  
            System.out.println(keyword +  "," + MsgType);
            
            switch(MsgType){
            case "text":
            	if(null!=keyword&&!keyword.equals(""))  
                {   
            		if(Code.isCode(keyword)){
            			Code c = new Code(keyword);
            			String msg = c.Execute(); 
            			this.print(returnText.responsebyText(msg, toUsername, fromUsername));  
            		}
            		else{
                		getBotMsg gbm = new getBotMsg();
                		String botmsg = gbm.getMsg(keyword);
                        this.print(returnText.responsebyText(botmsg, toUsername, fromUsername)); 
            		} 
                }
            	else{  
                    this.print("Input something...");  
                }  
            	break;
            case "event":
            	String event = root.elementTextTrim("Event");
            	switch(event){
            	case "subscribe":
            		this.print(returnText.responsebyText("感谢关注！", toUsername, fromUsername));
            		break;
            	case "CLICK":
                	String eventkey = root.elementTextTrim("EventKey");
                	System.out.println(eventkey);
                	switch(eventkey){
                	case "01_item":
                		JDBCUtil jdbc = new JDBCUtil();
                		String sql = "SELECT * FROM TABLE1";
                		this.print(returnText.responsebyText(jdbc.query(sql).toString(), toUsername, fromUsername));
                		break;
                	case "02_item":
                		getBotMsg gbm = new getBotMsg();
                		String botmsg = gbm.getMsg("你是谁");
                		this.print(returnText.responsebyText(botmsg, toUsername, fromUsername));
                		break;
                	}
            		break;
            	default:
            		System.out.println(postStr);
            		break;
            	}
            	break;
            default:
            	this.print(returnText.responsebyText("Input something...", toUsername, fromUsername));
            }
            
  
        }else {  
            this.print("");  
        }  
    }  
    //微信接口验证  
    public boolean checkSignature(HttpServletRequest request, HttpServletResponse response){  
        String signature = request.getParameter("signature");  
        String timestamp = request.getParameter("timestamp");  
        String nonce = request.getParameter("nonce");  
        String token=TOKEN;  
        
        String[] tmpArr={token,timestamp,nonce};  
        Arrays.sort(tmpArr);  
        String tmpStr=this.ArrayToString(tmpArr);  
        tmpStr=this.SHA1Encode(tmpStr);  
        if(tmpStr.equalsIgnoreCase(signature)){  
            return true;  
        }else{  
            return false;  
        }  
    }  
    //向请求端发送返回数据  
    public void print(String content){  
        try{  
            final_response.getWriter().print(content);  
            final_response.getWriter().flush();  
            final_response.getWriter().close();  
        }catch(Exception e){  
              
        }  
    }  
    //数组转字符串  
    public String ArrayToString(String [] arr){  
        StringBuffer bf = new StringBuffer();  
        for(int i = 0; i < arr.length; i++){  
         bf.append(arr[i]);  
        }  
        return bf.toString();  
    }  
    //sha1加密  
    public String SHA1Encode(String sourceString) {  
        String resultString = null;  
        try {  
           resultString = new String(sourceString);  
           MessageDigest md = MessageDigest.getInstance("SHA-1");  
           resultString = byte2hexString(md.digest(resultString.getBytes()));  
        } catch (Exception ex) {  
        }  
        return resultString;  
    }  
    public final String byte2hexString(byte[] bytes) {  
        StringBuffer buf = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            if (((int) bytes[i] & 0xff) < 0x10) {  
                buf.append("0");  
            }  
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));  
        }  
        return buf.toString().toUpperCase();  
    }  
    //从输入流读取post参数  
    public String readStreamParameter(ServletInputStream in){  
        StringBuilder buffer = new StringBuilder();  
        BufferedReader reader=null;  
        try{  
            reader = new BufferedReader(new InputStreamReader(in, "utf-8"));  
            String line=null;  
            while((line = reader.readLine())!=null){  
                buffer.append(line);  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(null!=reader){  
                try {  
                    reader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return buffer.toString();  
    }  
	
}
