package id.zar.smartkantin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.FoodMenu;
import id.zar.smartkantin.RequestModel.FormFoodMenu;
import id.zar.smartkantin.service.IFoodMenuService;

@RestController
@RequestMapping("/api/food-menu")
public class FoodMenuController {
//	private final IFoodMenuRepository foodMenuRepo;
//	public FoodMenuController(IFoodMenuRepository foodMenuRepo) {
//		this.foodMenuRepo = foodMenuRepo;
//	}
	
	@Autowired
	private IFoodMenuService foodMenuService;
	
	@GetMapping("")
	public Iterable<FoodMenu> getFoodMenus()
	{
//		var result = new ArrayList<FoodMenu>();
//		return result;
//		var result = foodMenuRepo.findAll();
//		return result;
		
		var result = foodMenuService.getAll();
		return result;
	}
	
	@PostMapping("/add")
	public FoodMenu add(@RequestBody FormFoodMenu item)
	{
//		var baru = new FoodMenu();
//		baru.setNama("menu baru");
		
		var baru = item.asNewFoodMenu();
		
		foodMenuService.add(baru);
		return baru;
	}
}
