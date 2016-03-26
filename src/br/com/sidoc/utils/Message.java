package br.com.sidoc.utils;

public class Message {
	private String style;
	private String message;
	private Boolean status;
	
	public Message(String message, String style) {
		this.message = message;
		this.style   = style;
		this.status  = true;
	}

	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		if(style!=null){
			this.style = style;
		}
		this.style = "success";
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String[] getMessage() {
		if(this.getStatus() == true){
			String[] messageArray = new String[2];
			messageArray[0] = style;
			messageArray[1] = message;
			return messageArray;
		}
		return null;
	}	
	
}
