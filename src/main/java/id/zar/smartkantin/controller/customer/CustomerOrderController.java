package id.zar.smartkantin.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.CustomerOrder;
import id.zar.smartkantin.RequestModel.FormAddOrder;
import id.zar.smartkantin.service.ICustomerOrderService;
import id.zar.smartkantin.tools.SessionTools;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/customer/order")
@SecurityRequirement(name = "Authorization")
public class CustomerOrderController {
	@Autowired
	private ICustomerOrderService svc;
	
	@GetMapping("")
	public List<CustomerOrder> getAll(Authentication sess)
	{
		var userId = SessionTools.getUserIdFromAuth(sess);
		var r = svc.getAll(userId);
//		return new ArrayList<CustomerOrder>();
		return r;
	}
	
	@PostMapping("/add")
	public CustomerOrder add(Authentication sess, @RequestBody FormAddOrder form)
	{
		var userId = SessionTools.getUserIdFromAuth(sess);
		var r = svc.create(userId, form.getCartIds());
		return r;
	}
	
}
