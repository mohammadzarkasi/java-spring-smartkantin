package id.zar.smartkantin.service;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.CustomerCartItem;

public interface ICustomerCartService {
	CustomerCartItem add(CustomerCartItem item);
	List<CustomerCartItem> getAllByUserId(UUID userId);
}
