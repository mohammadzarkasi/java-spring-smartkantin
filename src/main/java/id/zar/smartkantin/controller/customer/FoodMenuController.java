package id.zar.smartkantin.controller.customer;


import java.util.UUID;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.FoodMenu;
import id.zar.smartkantin.RequestModel.FormFoodMenu;
import id.zar.smartkantin.service.IFoodMenuService;

@RestController
@RequestMapping("/api/customer/food-menu")
public class FoodMenuController {
	
	@Autowired
	private IFoodMenuService foodMenuService;
	
	@GetMapping("")
	public Iterable<FoodMenu> getFoodMenus()
	{		
		var result = foodMenuService.getAll();
		return result;
	}
	
	@GetMapping("/detail")
	public FoodMenu detail(@RequestParam("id") UUID id) throws InstanceNotFoundException
	
	{
		var result = foodMenuService.getById(id);
		if(result == null)
		{
			throw new InstanceNotFoundException("ID not found");
		}
		return result;
	}
	
	
}
