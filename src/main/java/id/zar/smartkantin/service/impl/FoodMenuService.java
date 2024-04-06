package id.zar.smartkantin.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.FoodMenu;
import id.zar.smartkantin.repository.IFoodMenuRepository;
import id.zar.smartkantin.service.IFoodMenuService;

@Service
public class FoodMenuService implements IFoodMenuService{

	@Autowired
	private IFoodMenuRepository foodMenuRepo;
	
	@Transactional
	@Override
	public Iterable<FoodMenu> getAll() {
		return foodMenuRepo.getAll();
	}

	
	@Transactional
	@Override
	public FoodMenu add(FoodMenu baru) {
		foodMenuRepo.add(baru);
		return baru;
	}


	@Transactional
	@Override
	public FoodMenu getById(UUID id) {
		var result = foodMenuRepo.getById(id);
		
		return result;
	}


	@Transactional
	@Override
	public FoodMenu update(FoodMenu oldItem, FoodMenu newItem) {
		var result = foodMenuRepo.update(oldItem, newItem);
		return result;
	}


	@Transactional
	@Override
	public Iterable<FoodMenu> getByOwnerId(UUID ownerId) {
		System.out.println("find by owner id: " + ownerId);
		var result = foodMenuRepo.getByOwnerId(ownerId);
		return result;
	}


//	@Transactional
//	@Override
//	public FoodMenu update(UUID id, FoodMenu newItem) {
//		var result = foodMenuRepo.update(id, newItem);
//		return result;
//	}

}
