package id.zar.smartkantin.service;

import java.util.List;

import id.zar.smartkantin.DbModel.CustomerOrder;

public interface ICustomerOrderService {
	List<CustomerOrder> getAll();
}
