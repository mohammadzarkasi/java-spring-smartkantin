package id.zar.smartkantin.RequestModel;

public class FormAddPaymentMethod {
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if(code == null || code.isEmpty())
		{
			this.code = "no code";
		}
		else
		{			
			this.code = code;
		}
		this.code = this.code.toLowerCase();
	}
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
