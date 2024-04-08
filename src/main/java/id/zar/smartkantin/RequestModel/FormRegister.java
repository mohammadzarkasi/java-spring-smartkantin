package id.zar.smartkantin.RequestModel;

import id.zar.smartkantin.DbModel.MyUser;

public class FormRegister extends FormLogin{
//	private String username;
	private String email;
//	private String password;
	
	private String password2;
	
	
	public MyUser asMyUser()
	{
		var user = new MyUser();
//		user.setEmail(this.getEmail());
		user.setPassword(this.getPassword());
		user.setUsername(this.getUsername());
		
		return user;
	}
	
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	


}
