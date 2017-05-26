package bean;

import java.util.ArrayList;
import java.util.List;

public class MultiTextMsgList {
	private List<MultiTextMsgItem> items;
	
	public MultiTextMsgList(){
		this.items = new ArrayList<MultiTextMsgItem>();
	}
	
	public int size(){
		return items.size();
	}
	
	public void add(MultiTextMsgItem item){
		this.items.add(item);
	}
	
	public String toString(){
		String text = "<ArticleCount>" + this.size() + "</ArticleCount>" +
						"<Articles>";
		for(MultiTextMsgItem item : items){
			text += item.toString();
		}
		text += "</Articles>";
		
		return text;
	}

}
