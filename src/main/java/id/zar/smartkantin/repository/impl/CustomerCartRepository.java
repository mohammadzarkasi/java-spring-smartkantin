package id.zar.smartkantin.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import id.zar.smartkantin.DbModel.CustomerCartItem;
import id.zar.smartkantin.repository.ICustomerCartRepository;
import jakarta.persistence.EntityManager;

@Repository
public class CustomerCartRepository implements ICustomerCartRepository{
	@Autowired
	private EntityManager entityManager;
	
	private Session getSession()
	{
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	public CustomerCartItem getById(UUID id) {
		var s = getSession();
		var r = s.find(CustomerCartItem.class, id);
		return r;
	}

	@Override
	public List<CustomerCartItem> getAll() {
		var s = getSession();
//		var q = s.createQuery("from CustomerCartItem c", CustomerCartItem.class);
//		return q.getResultList();
//		var c = s.createQuery(CustomerCartItem.class);
		
		var cb = s.getCriteriaBuilder();
		var cr = cb.createQuery(CustomerCartItem.class);
		var root = cr.from(CustomerCartItem.class);
		cr.select(root);
		
		var q = s.createQuery(cr);
		return q.getResultList();
	}

	@Override
	public List<CustomerCartItem> getByUserId(UUID userId) {
		var s = getSession();
//		var q = s.createQuery("from CustomerCartItem c where c.userId = :userId", CustomerCartItem.class);
//		q.setParameter("userId", userId);
//		return q.getResultList();
		
		var cb = s.getCriteriaBuilder();
		var cr = cb.createQuery(CustomerCartItem.class);
		var root = cr.from(CustomerCartItem.class);
		cr.select(root);
		cr.where(cb.equal(root.get("userId"), userId));
		
		var q = s.createQuery(cr);
		return q.getResultList();
	}

	@Override
	public List<CustomerCartItem> getByFoodId(UUID foodMenuId) {
		var s = getSession();
		
		var cb = s.getCriteriaBuilder();
		var cr = cb.createQuery(CustomerCartItem.class);
		var root = cr.from(CustomerCartItem.class);
		cr.select(root);
		cr.where(cb.equal(root.get("foodMenuId"), foodMenuId));
		
		var q = s.createQuery(cr);
		return q.getResultList();
		
//		var q = s.createQuery("from CustomerCartItem c where c.foodMenuId = :foodMenuId", CustomerCartItem.class);
//		q.setParameter("foodMenuId", foodMenuId);
//		return q.getResultList();
	}

	@Override
	public void deleteCartItem(UUID id) {
		var s = getSession();
		var item = s.find(CustomerCartItem.class, id);
		s.evict(item);
		
		
//		var q = s.createQuery("delete CustomerCartItem c where c.id = :id", CustomerCartItem.class);
//		q.setParameter("id", id);
//		q.executeUpdate();
		
	}

	@Override
	public CustomerCartItem updateCartItem(UUID id, int qty) {
		var s = getSession();
		var item = getById(id);
		item.setQty(qty);
		item.setUpdatedAt(LocalDateTime.now());
		
		s.merge(item);
		return item;
		
//		var q = s.createQuery("update CustomerCartItem c set c.qty = :qty where c.id = :id", CustomerCartItem.class);
//		q.setParameter("id", id);
//		q.setParameter("qty", qty);
//		q.executeUpdate();
	}

	@Override
	public CustomerCartItem updateCartItem(CustomerCartItem item) {
		var s = getSession();
		item.setUpdatedAt(LocalDateTime.now());
		s.merge(item);
		return item;
	}

	@Override
	public CustomerCartItem add(CustomerCartItem item) {
		var s = getSession();
		item.setCreatedAt(LocalDateTime.now());
		s.persist(item);
		return item;
	}

	@Override
	public List<CustomerCartItem> getByFoodId(UUID userId, UUID foodMenuId) {
		System.out.println("getByFoodId(UUID userId, UUID foodMenuId) " + userId + ", " + foodMenuId);
		var s = getSession();
		
		var cb = s.getCriteriaBuilder();
		var cr = cb.createQuery(CustomerCartItem.class);
		var root = cr.from(CustomerCartItem.class);
		cr.select(root);
		var where1 = cb.equal(root.get("foodMenuId"), foodMenuId);
		var where2 = cb.equal(root.get("userId"), userId);
//		cr.where(cb.equal(root.get("foodMenuId"), foodMenuId));
//		cr.where(cb.equal(root.get("userId"), userId));
		cr.where(cb.and(where1, where2));
		
		var q = s.createQuery(cr);
		return q.getResultList();
	}

}
