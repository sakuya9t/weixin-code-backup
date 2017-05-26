package bean;

public class MultiTextMsgItem {
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	
	public void setTitle(String title){
		this.Title = title;
	}
	
	public void setDescription(String discription){
		this.Description = discription;
	}
	
	public void setPicUrl(String picurl){
		this.PicUrl = picurl;
	}
	
	public void setUrl(String url){
		this.Url = url;
	}
	
	public String toString(){
		String textTpl = "<item>" +
				"<Title><![CDATA[%1$s]]></Title>" +
				"<Description><![CDATA[%2$s]]></Description>" +
				"<PicUrl><![CDATA[%3$s]]></PicUrl>" +
				"<Url><![CDATA[%4$s]]></Url>" +
				"</item>";
		return String.format(textTpl, Title, Description, PicUrl, Url); 
	}

}
