package id.zar.smartkantin.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.CustomerCartItem;
import id.zar.smartkantin.repository.ICustomerCartRepository;
import id.zar.smartkantin.service.ICustomerCartService;

@Service
public class CustomerCartService implements ICustomerCartService{

	@Autowired
	private ICustomerCartRepository repo;
	
	@Transactional
	@Override
	public CustomerCartItem add(CustomerCartItem item) {
		var existings = repo.getByFoodId(item.getUserId(), item.getFoodMenuId());
		if(existings.isEmpty() == false)
		{
			var existing = existings.get(0);
			existing.setQty(item.getQty());
			
			repo.updateCartItem(existing);
			
			return existing;
		}
		return repo.add(item);
	}

	@Transactional
	@Override
	public List<CustomerCartItem> getAllByUserId(UUID userId) {
		return repo.getByUserId(userId);
	}

	@Transactional
	@Override
	public CustomerCartItem updateItem(UUID id, int qty) {
		return repo.updateCartItem(id, qty);
	}

	@Transactional
	@Override
	public CustomerCartItem getById(UUID id) {
		return repo.getById(id);
	}

	@Transactional
	@Override
	public CustomerCartItem updateItem(CustomerCartItem item, int qty) {
		item.setQty(qty);
		return repo.updateCartItem(item);
	}

	@Transactional
	@Override
	public void delete(CustomerCartItem item) {
		repo.deleteCartItem(item);
		
	}

}
