package id.zar.smartkantin.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import id.zar.smartkantin.DbModel.CustomerOrder;
import id.zar.smartkantin.DbModel.CustomerOrderDetail;
import id.zar.smartkantin.repository.ICustomerOrderRepository;
import jakarta.persistence.EntityManager;

@Repository
public class CustomerOrderRepository implements ICustomerOrderRepository{
	@Autowired
	private EntityManager entityManager;
	
	private Session getSession()
	{
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	public CustomerOrder getById(UUID id) {
		var sess = getSession();
		var r = sess.find(CustomerOrder.class, id);
		return r;
	}

	@Override
	public List<CustomerOrder> getAll() {
		var sess = getSession();
		var q = sess.createQuery("from CustomerOrder", CustomerOrder.class);
		var r = q.getResultList();
		return r;
	}

	@Override
	public List<CustomerOrder> getByUserId(UUID userId) {
		var sess = getSession();
		var cb = sess.getCriteriaBuilder();
		var cr = cb.createQuery(CustomerOrder.class);
		var root = cr.from(CustomerOrder.class);
		cr.select(root);
		cr.where(cb.equal(root.get("userId"), userId));
		
		var q = sess.createQuery(cr);
		
		return q.getResultList();
		
//		var q = sess.createQuery("from CustomerOrder o where o.userId = :userId", CustomerOrder.class);
//		q.setParameter("urderId", userId);
//		var r = q.getResultList();
//		return r;
	}

	@Override
	public List<CustomerOrderDetail> getDetails(UUID orderId) {
		var sess = getSession();
		var q = sess.createQuery("from CustomerOrderDetail od where od.orderId = :orderId", CustomerOrderDetail.class);
		q.setParameter("orderId", orderId);
		var r = q.getResultList();
		return r;
	}

	@Override
	public void addDetails(UUID orderId, List<CustomerOrderDetail> details) {
//		var sess = getSession();
		
		for(var d : details)
		{
			addDetail(orderId, d);
		}
		
	}

	@Override
	public CustomerOrderDetail addDetail(UUID orderId, CustomerOrderDetail d) {
		var sess = getSession();
		
		d.setCreatedAt(LocalDateTime.now());
		d.setOrderId(orderId);
		
		sess.persist(d);
		
		return d;
	}

	@Override
	public void deleteDetail(UUID orderId, List<UUID> detailIds) {
		for(var d : detailIds)
		{
			deleteDetail(orderId, d);
		}
		
	}

	@Override
	public void deleteDetail(UUID orderId, UUID detailId) {
		var sess = getSession();
		
		var q = sess.createQuery("delete CustomerOrderDetail where id = :id and orderId = :orderId", CustomerOrderDetail.class);
		q.setParameter("id", detailId);
		q.setParameter("orderId", orderId);
		q.executeUpdate();
		
	}

	@Override
	public void deleteAllDetail(UUID orderId) {
		var sess = getSession();
		
		var q = sess.createQuery("delete CustomerOrderDetail where orderId = :orderId", CustomerOrderDetail.class);
		
		q.setParameter("orderId", orderId);
		q.executeUpdate();
		
	}

	@Override
	public void deleteOrder(UUID orderId) {
		var sess = getSession();
		
		var q = sess.createQuery("delete CustomerOrder where id = :id", CustomerOrderDetail.class);
		
		q.setParameter("id", orderId);
		q.executeUpdate();
	}

	@Override
	public CustomerOrder add(CustomerOrder newOrder) {
		var sess = getSession();

		newOrder.setCreatedAt(LocalDateTime.now());
	
		sess.persist(newOrder);
		
		return newOrder;
	}

	@Override
	public CustomerOrder update(CustomerOrder item) {
		var s = getSession();
		item.setUpdatedAt(LocalDateTime.now());
		
		s.merge(item);
		
		return item;
	}

}
