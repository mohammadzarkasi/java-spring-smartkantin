package id.zar.smartkantin.controller.vendor;

import java.util.UUID;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.FoodMenu;
import id.zar.smartkantin.RequestModel.FormFoodMenu;
import id.zar.smartkantin.security.CustomUsernamePasswordAuthToken;
import id.zar.smartkantin.service.IFoodMenuService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/vendor/food-menu")
public class VendorFoodMenuController {

	@Autowired
	private IFoodMenuService service;
	
	@GetMapping("")
	public Iterable<FoodMenu> getAll(Authentication session)
	{
		UUID userId = new UUID(0,0);
		if( session instanceof CustomUsernamePasswordAuthToken)
		{
			System.out.println("vendor food: session: ------" );
			userId = ((CustomUsernamePasswordAuthToken) session).getUserId();
		}
//		var user = (CustomUser) session.getCredentials();
//		var result = service.getByOwnerId(new UUID(0, 0));
		var result = service.getByOwnerId(userId);
//		var result = service.getByOwnerId(user.getUserId());
		return result;
	}
	
	@PostMapping("/add")
	public FoodMenu add(@RequestBody FormFoodMenu item)
	{	
		var baru = item.asNewFoodMenu();
		
		service.add(baru);
	
		return baru;
	}
	
	@PostMapping("/update")
	public FoodMenu update(@RequestBody FormFoodMenu menu, @RequestParam("id-menu") UUID menuId) throws Exception
	{
		var target = service.getById(menuId);
		if(target == null)
		{
			throw new InstanceNotFoundException("ID Not Found");
		}
		
//		var result = foodMenuService.update(menuId, menu.asNewFoodMenu());
		var result = service.update(target, menu.asNewFoodMenu());
		
		return result;
	}
}
