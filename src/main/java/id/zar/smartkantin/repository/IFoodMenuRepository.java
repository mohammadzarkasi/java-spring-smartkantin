package id.zar.smartkantin.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import id.zar.smartkantin.DbModel.FoodMenu;

//public interface IFoodMenuRepository extends CrudRepository<FoodMenu, UUID>
//{
//	
//}


public interface IFoodMenuRepository
{
	Iterable<FoodMenu> getAll();
	FoodMenu add(FoodMenu baru);
	FoodMenu getById(UUID id);
//	FoodMenu update(UUID id, FoodMenu oldItem);
	FoodMenu update(FoodMenu oldItem, FoodMenu newItem);
}