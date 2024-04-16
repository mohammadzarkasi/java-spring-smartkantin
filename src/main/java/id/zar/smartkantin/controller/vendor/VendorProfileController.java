package id.zar.smartkantin.controller.vendor;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.Vendor;
import id.zar.smartkantin.RequestModel.FormAddVendor;
import id.zar.smartkantin.security.CustomUsernamePasswordAuthToken;
import id.zar.smartkantin.service.IVendorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/vendor/profile")
@SecurityRequirement(name = "Authorization")
public class VendorProfileController {
	@Autowired
	private IVendorService vendorSvc;
	
	@GetMapping("")
	public Vendor myProfile(Authentication sess) throws Exception
	{
		UUID userId = new UUID(0,0);
		if(sess instanceof CustomUsernamePasswordAuthToken)
		{
			System.out.println("vendor food: session: ------" );
			userId = ((CustomUsernamePasswordAuthToken) sess).getUserId();
		}
		
		var vendorMe = vendorSvc.getById(userId);
		
		if(vendorMe == null)
		{
			throw new Exception("Data Vendor Anda tidak tersedia");
		}
		
		return vendorMe;
	}
	
	@GetMapping("/create")
	public Vendor create(Authentication sess, @RequestBody FormAddVendor form) throws Exception
	{
		UUID userId = new UUID(0,0);
		if(sess instanceof CustomUsernamePasswordAuthToken)
		{
			System.out.println("vendor food: session: ------" );
			userId = ((CustomUsernamePasswordAuthToken) sess).getUserId();
		}
		
		var vendorMe = vendorSvc.getById(userId);
		
		if(vendorMe != null)
		{
			throw new Exception("Anda sudah memiliki data vendor");
		}
		
		var vendor = form.asVendor();
		vendor.setUserId(userId);
		
		return vendorSvc.add(vendor);
		
	}
	
	@GetMapping("/update")
	public Vendor update(Authentication sess, @RequestBody FormAddVendor form) throws Exception
	{
		UUID userId = new UUID(0,0);
		if(sess instanceof CustomUsernamePasswordAuthToken)
		{
			System.out.println("vendor food: session: ------" );
			userId = ((CustomUsernamePasswordAuthToken) sess).getUserId();
		}
		
		var vendorMe = vendorSvc.getById(userId);
		
		if(vendorMe == null)
		{
			throw new Exception("Anda belum memiliki data vendor");
		}
		
		var vendor = vendorSvc.getVendorByUserId(userId);
		
		vendor = form.merge(vendor);
		
		return vendorSvc.update(vendor);
	}
}
