package id.zar.smartkantin.service;

import id.zar.smartkantin.DbModel.FoodMenu;

public interface IFoodMenuService {
	Iterable<FoodMenu> getAll();
	
	FoodMenu add(FoodMenu baru);
}
