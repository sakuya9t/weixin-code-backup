package bean;

import tools.codeUtils;
import util.QiuqianUtil;
import util.getCap4jDataTest;

public class Code {
	private String command = "";
	private String args = "";
	
	public Code(String msg){
		String s = msg.replace("Code:", "");
		s = s.replace("Code：", "");
		s = s.replace(" ", "");
		this.command = this.getCommand(s);
		this.args = this.getArgs(s);
	}
	
	public static boolean isCode(String msg){
		if(msg.indexOf("Code:") == 0 || msg.indexOf("Code：") == 0)
			return true;
		else
			return false;
	}
	
	public String Execute(){
		String msg = "";
		switch(command){
		case "getipaddr":
			msg = codeUtils.getipAddr();
			break;
		case "cap4jtest":
			getCap4jDataTest c4test = new getCap4jDataTest();
			msg = c4test.test4();
			break;
		case "求签":
			msg = QiuqianUtil.Qiuqian(args);
			break;
		default:
			msg = "非有效的code";
			break;
		}
		
		return msg;
	}
	
	private String getCommand(String s){
		if(s.indexOf("getipaddr") == 0)
			return "getipaddr";
		else if(s.indexOf("cap4jtest") == 0)
			return "cap4jtest";
		else if(s.indexOf("求签") == 0)
			return "求签";
		else
			return "";
	}
	
	private String getArgs(String s){
		String command = this.getCommand(s.replace(" ", ""));
		return s.replace(command, "");
	}
}
