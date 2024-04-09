package id.zar.smartkantin.repository;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.PaymentMethod;

public interface IPaymentMethodRepository {
	PaymentMethod getById(UUID id);
	PaymentMethod getByIdAndActive(UUID id);
	PaymentMethod getByCodeAndActive(String code);
	List<PaymentMethod> getAll();
	List<PaymentMethod> getAllActive();
	PaymentMethod update(PaymentMethod item);
	PaymentMethod add(PaymentMethod item);
	void delete(PaymentMethod item);
}
