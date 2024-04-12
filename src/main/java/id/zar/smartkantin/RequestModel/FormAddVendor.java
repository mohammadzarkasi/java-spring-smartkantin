package id.zar.smartkantin.RequestModel;

import id.zar.smartkantin.DbModel.Vendor;

public class FormAddVendor {
	private String name;
	private String pict;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPict() {
		return pict;
	}
	public void setPict(String pict) {
		this.pict = pict;
	}
	
	public Vendor asVendor()
	{
		var v = new Vendor();
		v.setName(name);
		v.setPictPath(pict);
		
		return v;
	}
	public Vendor merge(Vendor vendor) {
		vendor.setName(name);
		vendor.setPictPath(pict);
		
		return vendor;
	}
}
