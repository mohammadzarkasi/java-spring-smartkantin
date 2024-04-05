package id.zar.smartkantin.service;

import java.util.UUID;

import id.zar.smartkantin.DbModel.FoodMenu;

public interface IFoodMenuService {
	Iterable<FoodMenu> getAll();
	
	FoodMenu add(FoodMenu baru);
	FoodMenu getById(UUID id);
	
	FoodMenu update(UUID id, FoodMenu newItem);
}
