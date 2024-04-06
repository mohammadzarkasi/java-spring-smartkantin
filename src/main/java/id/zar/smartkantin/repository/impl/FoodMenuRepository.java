package id.zar.smartkantin.repository.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import id.zar.smartkantin.DbModel.FoodMenu;
import id.zar.smartkantin.repository.IFoodMenuRepository;
import jakarta.persistence.EntityManager;

@Repository
public class FoodMenuRepository implements IFoodMenuRepository
{
	@Autowired
	private EntityManager entityManager;

	@Override
	public Iterable<FoodMenu> getAll() {
		Session session =  entityManager.unwrap(Session.class);
		
		var query = session.createQuery("select fm from FoodMenu fm order by fm.createdAt desc", FoodMenu.class);
		query.setFirstResult(0);
		query.setMaxResults(50);
		
		var result = query.getResultList();
		return result;
	}

	@Override
	public FoodMenu add(FoodMenu baru) {
		var session  = entityManager.unwrap(Session.class);
		baru.setCreatedAt(LocalDateTime.now());
		
		session.persist(baru);
		
		return baru;
	}

	@Override
	public FoodMenu getById(UUID id) {
		var session = entityManager.unwrap(Session.class);
		
		var result = session.find(FoodMenu.class, id);
		
		return result;
	}

	@Override
	public FoodMenu update(FoodMenu oldItem, FoodMenu newItem) {
		var session = entityManager.unwrap(Session.class);
		
		oldItem.setUpdatedAt(LocalDateTime.now());
		oldItem.setNama(newItem.getNama());
		oldItem.setOwnerId(newItem.getOwnerId());
		oldItem.setPrice(newItem.getPrice());
		
		session.persist(oldItem);
		
		return oldItem;
	}
	
	private Session getSession()
	{
		return entityManager.unwrap(Session.class);
	}

	@Override
	public Iterable<FoodMenu> getByOwnerId(UUID ownerId) {
		var session = getSession();
		
		var query = session.createQuery("from FoodMenu f where f.ownerId = :id", FoodMenu.class);
		query.setParameter("id", ownerId);
		
		var result = query.getResultList();
		return result;
	}

//	@Override
//	public FoodMenu update(UUID id, FoodMenu newItem) {
//		var session = entityManager.unwrap(Session.class);
//		
//		var target = getById(id);
//		
//		if(target == null)
//		{
//			return null;
//		}
//		
//		target.setUpdatedAt(LocalDateTime.now());
//		target.setNama(newItem.getNama());
//		target.setOwnerId(newItem.getOwnerId());
//		target.setPrice(newItem.getPrice());
//		
//		session.persist(target);
//		
//		return target;
//	}
	

}
