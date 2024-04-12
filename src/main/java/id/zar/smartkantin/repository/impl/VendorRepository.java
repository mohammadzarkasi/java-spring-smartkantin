package id.zar.smartkantin.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import id.zar.smartkantin.DbModel.Vendor;
import id.zar.smartkantin.repository.IVendorRepository;
import jakarta.persistence.EntityManager;

@Repository
public class VendorRepository implements IVendorRepository{
	@Autowired
	private EntityManager entityManager;
	
	private Session getSession()
	{
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	public Vendor getById(UUID id) {
		var s = getSession();
		var cb = s.getCriteriaBuilder();
		var cq = cb.createQuery(Vendor.class);
		var root = cq.from(Vendor.class);
		cq.select(root);
		
		var where1 = cb.equal(root.get("id"), id);
		var where2 = cb.isNull(root.get("deletedOn"));
		
		cq.where(cb.and(where1, where2));
		
		var q = s.createQuery(cq);
		
		var r = q.getResultList();
		if(r.isEmpty())
		{
			return null;
		}
		return r.get(0);
	}

	@Override
	public List<Vendor> getAll() {
		var s = getSession();
		var cb = s.getCriteriaBuilder();
		var cq = cb.createQuery(Vendor.class);
		var root = cq.from(Vendor.class);
		cq.select(root);
		
		var where2 = cb.isNull(root.get("deletedOn"));
		
		cq.where(cb.and(where2));
		
		var q = s.createQuery(cq);
		
		var r = q.getResultList();
		return r;
	}

	@Override
	public List<Vendor> getAllWithDeleted() {
		var s = getSession();
		var cb = s.getCriteriaBuilder();
		var cq = cb.createQuery(Vendor.class);
		var root = cq.from(Vendor.class);
		cq.select(root);
		
		var q = s.createQuery(cq);
		
		var r = q.getResultList();
		return r;
	}

	@Override
	public void delete(Vendor item) {
		var s = getSession();
		item.setDeletedOn(LocalDateTime.now());
		
		s.merge(item);
		
	}

	@Override
	public Vendor update(Vendor vendor) {
		var s = getSession();
		vendor.setUpdatedOn(LocalDateTime.now());
		
		s.merge(vendor);
		
		return vendor;
	}

	@Override
	public Vendor add(Vendor vendor) {
		var s = getSession();
		
		s.persist(vendor);

		return vendor;
	}

	@Override
	public Vendor getByUserId(UUID userId) {
		var s = getSession();
		var cb = s.getCriteriaBuilder();
		var cq = cb.createQuery(Vendor.class);
		var root = cq.from(Vendor.class);
		cq.select(root);
		
		var where1 = cb.equal(root.get("userId"), userId);
		var where2 = cb.isNull(root.get("deletedOn"));
		
		cq.where(cb.and(where1, where2));
		
		var q = s.createQuery(cq);
		
		var r = q.getResultList();
		if(r.isEmpty())
		{
			return null;
		}
		return r.get(0);
	}

}
