package id.zar.smartkantin.RequestModel;

import id.zar.smartkantin.DbModel.Role;

public class FormRole {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Role asRole()
	{
		var n = new Role();
		n.setName(name);
		return n;
	}
}
