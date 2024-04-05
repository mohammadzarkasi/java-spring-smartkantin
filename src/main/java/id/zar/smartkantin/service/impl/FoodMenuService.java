package id.zar.smartkantin.service.impl;

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

}
