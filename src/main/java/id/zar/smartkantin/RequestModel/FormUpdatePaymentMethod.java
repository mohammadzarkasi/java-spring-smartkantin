package id.zar.smartkantin.RequestModel;

public class FormUpdatePaymentMethod {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null || name.isEmpty())
		{
			this.name = "no name";
		}
		else
		{			
			this.name = name;
		}
	}
	
	
}
