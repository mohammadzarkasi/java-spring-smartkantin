package id.zar.smartkantin.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.CustomerCartItem;
import id.zar.smartkantin.RequestModel.FormAddCart;
import id.zar.smartkantin.service.ICustomerCartService;
import id.zar.smartkantin.tools.SessionTools;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/customer/cart")
@SecurityRequirement(name = "Authorization")
public class CustomerCartController {
	@Autowired
	private ICustomerCartService svc;
	
	@GetMapping("")
	public List<CustomerCartItem> getAll(Authentication sess)
	{
		var userId = SessionTools.getUserIdFromAuth(sess);
		return svc.getAllByUserId(userId);
	}
	
//	private CustomerCartItem fromForm(CustomerCartItem form)
//	{
//		var newItem = new CustomerCartItem();
//		newItem.setQty(form.getQty());
//		newItem.setFoodMenuId(form.getFoodMenuId());
//		return newItem;
//	}
	
	@PostMapping("/add")
	public CustomerCartItem add(Authentication sess, @RequestBody FormAddCart form)
	{
//		var newItem = fromForm(form);
		var userId = SessionTools.getUserIdFromAuth(sess);
		var c = new CustomerCartItem();
		c.setUserId(userId);
		c.setFoodMenuId(form.getFoodId());
		c.setQty(form.getQty());
		
		return svc.add(c);
		
	}
	
//	public CustomerCartItem update(Authentication sess, @Re)
}
