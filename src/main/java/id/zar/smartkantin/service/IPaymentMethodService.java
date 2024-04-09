package id.zar.smartkantin.service;

import java.util.List;

import id.zar.smartkantin.DbModel.PaymentMethod;

public interface IPaymentMethodService {
	List<PaymentMethod> getAll();
	PaymentMethod add(PaymentMethod item);
}
