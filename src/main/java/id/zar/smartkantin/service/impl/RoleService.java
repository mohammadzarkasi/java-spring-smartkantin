package id.zar.smartkantin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.RequestModel.FormRole;
import id.zar.smartkantin.repository.IRoleRepository;
import id.zar.smartkantin.service.IRoleService;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleRepository repo;
	
	@Transactional
	@Override
	public Role findByName(String name) {
		return repo.findByName(name);
	}

	@Transactional
	@Override
	public List<Role> findRolesOfUser(MyUser user) {
		return repo.findRolesOfUser(user);
	}

	@Transactional
	@Override
	public List<Role> addRoleToUser(MyUser user, Role role) {
		repo.addRoleToUser(user, role);
		return findRolesOfUser(user);
	}

	@Transactional
	@Override
	public Role add(FormRole newRole) {
		return repo.add(newRole);
	}

}
