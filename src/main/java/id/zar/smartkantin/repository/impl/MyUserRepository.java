package id.zar.smartkantin.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import id.zar.smartkantin.DbModel.MyUser;
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

	@Override
	public MyUser getByUsernameOrEmail(String usernameOrEmail) {
		var session = getSession();
		
		var cb = session.getCriteriaBuilder();
		var cq = cb.createQuery(MyUser.class);
		var root = cq.from(MyUser.class);
		cq.select(root);
		
		var where1 = cb.equal(root.get("email"), usernameOrEmail);
		var where2 = cb.equal(root.get("username"), usernameOrEmail);
		
		cq.where(cb.or(where1, where2));
		
		var query = session.createQuery(cq);
		
//		var query = session.createQuery("from MyUser u where u.email = :email or username = :username", MyUser.class);
//		query.setParameter("email", usernameOrEmail);
//		query.setParameter("username", usernameOrEmail);
		
		var result = query.getResultList();
		
		if(result.isEmpty())
		{
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<MyUser> getAll() {
		var sess = getSession();
		var query = sess.createQuery("select u from MyUser u order by u.username asc", MyUser.class);
		return query.getResultList();
	}

	@Override
	public MyUser getById(UUID id) {
		var sess = getSession();
		var res = sess.find(MyUser.class, id);
		return res;
	}

}
