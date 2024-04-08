package id.zar.smartkantin.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.DbModel.UserRole;
import id.zar.smartkantin.RequestModel.FormRole;
import id.zar.smartkantin.repository.IRoleRepository;
import jakarta.persistence.EntityManager;

@Repository
public class RoleRepository implements IRoleRepository{

	@Autowired
	private EntityManager entityManager;
	
	private Session getSession()
	{
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	public Role findByName(String name) {
		var session = getSession();
		
		var query = session.createQuery("from Role r where r.name = :name", Role.class);
		query.setParameter("name", name);
		
		var result = query.getResultList();
		
		if(result.isEmpty())
		{
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<Role> findRolesOfUser(MyUser user) {
		var session = getSession();
		var query = session.createQuery("select r from Role r inner join UserRole ur on r.id = ur.roleId where ur.userId = :userId", Role.class);
		query.setParameter("userId", user.getId());
		var result = query.getResultList();
		return result;
	}

	@Override
	public void addRoleToUser(MyUser user, Role role) {
		var session = getSession();
		
		var newUserRole = new UserRole();
		newUserRole.setRoleId(role.getId());
		newUserRole.setUserId(user.getId());
		newUserRole.setCreatedAt(LocalDateTime.now());
		
		session.persist(newUserRole);
	}

	@Override
	public Role add(FormRole newrole) {
		var sess = getSession();
		var role = newrole.asRole();
		
		role.setName(role.getName().toUpperCase());
		role.setCreatedAt(LocalDateTime.now());
		
		sess.persist(role);
		return role;
	}

	@Transactional
	@Override
	public List<Role> getAll() {
		var sess = getSession();
		var query = sess.createQuery("select r from Role r", Role.class);
		return query.getResultList();
	}

	@Override
	public Role getById(UUID id) {
		var sess = getSession();
		var res = sess.find(Role.class, id);
		return res;
	}

}
