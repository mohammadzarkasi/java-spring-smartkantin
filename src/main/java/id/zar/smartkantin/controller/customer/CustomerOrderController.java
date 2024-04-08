package id.zar.smartkantin.controller.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.CustomerOrder;
import id.zar.smartkantin.service.ICustomerOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/customer/order")
@SecurityRequirement(name = "Authorization")
public class CustomerOrderController {
	@Autowired
	private ICustomerOrderService svc;
	@GetMapping("")
	public List<CustomerOrder> getAll()
	{
		var r = svc.getAll();
//		return new ArrayList<CustomerOrder>();
		return r;
	}
}
