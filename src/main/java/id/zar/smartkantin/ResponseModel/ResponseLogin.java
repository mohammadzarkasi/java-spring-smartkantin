package id.zar.smartkantin.ResponseModel;

public class ResponseLogin {
	private Boolean status;
	private String msg;
	private String token;
	
	public ResponseLogin() 
	{
		status = false;
		msg = "";
		token = "";
	}
	
	public ResponseLogin(Boolean status, String msg) 
	{
		this.status = status;
		this.msg = msg;
		token = "";
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
