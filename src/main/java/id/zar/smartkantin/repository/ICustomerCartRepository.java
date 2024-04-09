package id.zar.smartkantin.repository;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.CustomerCartItem;

public interface ICustomerCartRepository {
	CustomerCartItem getById(UUID id);
	List<CustomerCartItem> getAll();
	List<CustomerCartItem> getByUserId(UUID userId);
	List<CustomerCartItem> getByFoodId(UUID foodMenuId);
	List<CustomerCartItem> getByFoodId(UUID userId, UUID foodMenuId);
	void deleteCartItem(UUID id);
	CustomerCartItem updateCartItem(UUID id, int qty);
	CustomerCartItem updateCartItem(CustomerCartItem item);
	CustomerCartItem add(CustomerCartItem item);
}
