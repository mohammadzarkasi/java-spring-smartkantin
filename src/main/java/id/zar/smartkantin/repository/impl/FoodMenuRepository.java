package id.zar.smartkantin.repository.impl;

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
		var query = session.createQuery("select fm from FoodMenu fm", FoodMenu.class);
		var result = query.getResultList();
		return result;
	}

	@Override
	public FoodMenu add(FoodMenu baru) {
		var session  = entityManager.unwrap(Session.class);
//		session.save(baru);
		session.persist(baru);
		return baru;
	}
	

}
