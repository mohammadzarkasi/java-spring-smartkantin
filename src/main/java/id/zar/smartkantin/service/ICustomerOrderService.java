package id.zar.smartkantin.service;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.CustomerOrder;

public interface ICustomerOrderService {
	List<CustomerOrder> getAll(UUID userId);
	CustomerOrder create(UUID userId, List<UUID> cartIds);
}
