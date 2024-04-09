package id.zar.smartkantin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.PaymentMethod;
import id.zar.smartkantin.repository.IPaymentMethodRepository;
import id.zar.smartkantin.service.IPaymentMethodService;

@Service
public class PaymentMethodService implements IPaymentMethodService{

	@Autowired
	private IPaymentMethodRepository paymentMethodRepo;
	
	@Transactional
	@Override
	public List<PaymentMethod> getAll() {
		return paymentMethodRepo.getAllActive();
	}

	@Transactional
	@Override
	public PaymentMethod add(PaymentMethod item) {
		var existing = paymentMethodRepo.getByCodeAndActive(item.getCode());
		if(existing != null)
		{
			return null;
		}
		return paymentMethodRepo.add(item);
	}
	
}
