package id.zar.smartkantin.repository;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.CustomerOrder;
import id.zar.smartkantin.DbModel.CustomerOrderDetail;

public interface ICustomerOrderRepository {
	CustomerOrder getById(UUID id);
	CustomerOrder add(CustomerOrder newOrder);
	CustomerOrder update(CustomerOrder item);
	List<CustomerOrder> getAll();
	List<CustomerOrder> getByUserId(UUID userId);
	void deleteOrder(UUID orderId);
	
	List<CustomerOrderDetail> getDetails(UUID orderId);
	void addDetails(UUID orderId, List<CustomerOrderDetail> details);
	CustomerOrderDetail addDetail(UUID orderId, CustomerOrderDetail detail);
	void deleteDetail(UUID orderId, List<UUID> detailIds);
	void deleteDetail(UUID orderId, UUID detailId);
	void deleteAllDetail(UUID orderId);
	
}
