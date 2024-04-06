package id.zar.smartkantin.repository.impl;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.RequestModel.FormRegister;
import id.zar.smartkantin.ResponseModel.ResponseMyUser;
import id.zar.smartkantin.repository.IMyUserRepository;
import jakarta.persistence.EntityManager;

@Repository
public class MyUserRepository implements IMyUserRepository{
	
	@Autowired
	private EntityManager entityManager;
	
	private Session getSession()
	{
		return entityManager.unwrap(Session.class);
	}

	@Override
	public MyUser add(MyUser user) {
		var session = getSession();
		
		user.setCreatedAt(LocalDateTime.now());
		user.setStatus("waiting-activation");
		
		session.persist(user);
		
		return user;
	}

	@Override
	public MyUser getByUsername(String username) {
		var session = getSession();
		
		var query = session.createQuery("from MyUser u where u.username = :username", MyUser.class);
		query.setParameter("username", username);
		
		var result = query.getResultList();
		
		if(result.isEmpty())
		{
			return null;
		}
		return result.get(0);
	}

	@Override
	public MyUser getByEmail(String email) {
		var session = getSession();
		
		var query = session.createQuery("from MyUser u where u.email = :email", MyUser.class);
		query.setParameter("email", email);
		
		var result = query.getResultList();
		
		if(result.isEmpty())
		{
			return null;
		}
		return result.get(0);
	}

}
