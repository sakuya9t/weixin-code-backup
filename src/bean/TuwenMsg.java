package bean;

import java.util.Date;

public class TuwenMsg {
	private String fromUsername;
	private String toUsername;
	private MultiTextMsgList msgList;
	
	public TuwenMsg(){
		fromUsername = "";
		toUsername = "";
		msgList = new MultiTextMsgList();
	}
	
	public void setFrom(String fromusername){
		this.fromUsername = fromusername;
	}
	
	public void setTo(String tousername){
		this.toUsername = tousername;
	}
	
	public void setMsglist(MultiTextMsgList msglist){
		this.msgList = msglist;
	}
	
	public String toString(){
		String textTpl = "<xml>" +
				"<ToUserName><![CDATA[%1$s]]></ToUserName>" + 
				"<FromUserName><![CDATA[%2$s]]></FromUserName>" +
				"<CreateTime>%3$s</CreateTime>" +
				"<MsgType><![CDATA[news]]></MsgType>";
				
				String time = new Date().getTime()+"";
				String text = String.format(textTpl, toUsername, fromUsername, time);
				
				text += msgList.toString();
				text += "</xml>";
				
				return text;	
	}
}
