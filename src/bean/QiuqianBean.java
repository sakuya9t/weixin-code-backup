package bean;

import util.TextUtil;

public class QiuqianBean {
	
	public static String pick(int id){
		String t=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
		String url = "http://qiuqian.sweetfree.net/xhtml/" + id + ".html";
		int num=t.indexOf("WEB-INF");
		MultiTextMsgItem item = new MultiTextMsgItem();
		item.setUrl(url);
		item.setDescription("");
		item.setPicUrl("");
		item.setTitle("");
		
		String path=t.substring(1,num).replace('/', '\\')+"resources\\qianAbstract.txt";
		String qian = TextUtil.getTextfromFile(path, id);
		qian += ("\n解签链接： " + url);
		//System.out.println(qian.getBytes().length);
		return qian;
	}
	
}
