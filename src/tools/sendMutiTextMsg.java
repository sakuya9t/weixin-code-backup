package tools;

import bean.MultiTextMsgList;
import bean.TuwenMsg;

public class sendMutiTextMsg {
	public static String responsebyMultiText(MultiTextMsgList msgList, String fromUsername, String toUsername){
		TuwenMsg msg = new TuwenMsg();
		msg.setFrom(fromUsername);
		msg.setTo(toUsername);
		msg.setMsglist(msgList);
		return msg.toString();
	}
}
