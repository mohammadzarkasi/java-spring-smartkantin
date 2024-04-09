package id.zar.smartkantin.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.PaymentMethod;
import id.zar.smartkantin.RequestModel.FormAddPaymentMethod;
import id.zar.smartkantin.service.IPaymentMethodService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/admin/payment-method")
@SecurityRequirement(name = "Authorization")
public class PaymentMethodManagementController {
	
	@Autowired
	private IPaymentMethodService paymentMethodSvc;

	@GetMapping("")
	public List<PaymentMethod> getAll()
	{
		return paymentMethodSvc.getAll();
	}
	
	@PostMapping("/add")
	public PaymentMethod add(@RequestBody FormAddPaymentMethod form) throws Exception
	{
		var pm = new PaymentMethod();
		pm.setCode(form.getCode());
		pm.setName(form.getName());
		
		var r = paymentMethodSvc.add(pm);
		
		if(r == null)
		{
			throw new Exception("Code has been used");
		}
		
		return r;
	}
}
