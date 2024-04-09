package id.zar.smartkantin.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import id.zar.smartkantin.DbModel.PaymentMethod;
import id.zar.smartkantin.repository.IPaymentMethodRepository;
import jakarta.persistence.EntityManager;

@Repository
public class PaymentMethodRepository implements IPaymentMethodRepository{

	@Autowired
	private EntityManager entityManager;
	
	private Session getSession()
	{
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	public PaymentMethod getById(UUID id) {
		var s = getSession();
		var r = s.find(PaymentMethod.class, id);
		return r;
	}

	@Override
	public List<PaymentMethod> getAll() {
		var s = getSession();
		var cb = s.getCriteriaBuilder();
		var cq = cb.createQuery(PaymentMethod.class);
		var root = cq.from(PaymentMethod.class);
		cq.select(root);
		var q = s.createQuery(cq);
		return q.getResultList();
	}

	@Override
	public List<PaymentMethod> getAllActive() {
		var s = getSession();
		var cb = s.getCriteriaBuilder();
		var cq = cb.createQuery(PaymentMethod.class);
		var root = cq.from(PaymentMethod.class);
		cq.select(root);
		
		var where1 = cb.isNull(root.get("deletedAt"));
		cq.where(where1);
		
		var q = s.createQuery(cq);
		return q.getResultList();
	}

	@Override
	public PaymentMethod update(PaymentMethod item) {
		var s = getSession();
		item.setUpdatedAt(LocalDateTime.now());
		
		s.merge(item);
		
		return item;
	}

	@Override
	public PaymentMethod add(PaymentMethod item) {
		var s = getSession();
		
		item.setCreatedAt(LocalDateTime.now());
		
		s.persist(item);
		
		return item;
	}

	@Override
	public void delete(PaymentMethod item) {
		var s = getSession();
		
		item.setDeletedAt(LocalDateTime.now());
		
		s.merge(item);
		
	}

	@Override
	public PaymentMethod getByIdAndActive(UUID id) {
		var s = getSession();
		var cb = s.getCriteriaBuilder();
		var cq = cb.createQuery(PaymentMethod.class);
		var root = cq.from(PaymentMethod.class);
		cq.select(root);
		
		var where1 = cb.isNull(root.get("deletedAt"));
		var where2 = cb.equal(root.get("id"), id);
		cq.where(cb.and(where1, where2));
		
		var q = s.createQuery(cq);
		var r = q.getResultList();
		
		if(r.isEmpty() == false)
		{
			return r.get(0);
		}
		
		return null;
	}

	@Override
	public PaymentMethod getByCodeAndActive(String code) {
		var s = getSession();
		var cb = s.getCriteriaBuilder();
		var cq = cb.createQuery(PaymentMethod.class);
		var root = cq.from(PaymentMethod.class);
		cq.select(root);
		
		var where1 = cb.isNull(root.get("deletedAt"));
		var where2 = cb.equal(root.get("code"), code);
		cq.where(cb.and(where1, where2));
		
		var q = s.createQuery(cq);
		var r = q.getResultList();
		
		if(r.isEmpty() == false)
		{
			return r.get(0);
		}
		
		return null;
	}

}
