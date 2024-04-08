package id.zar.smartkantin.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.RequestModel.FormRole;
import id.zar.smartkantin.repository.IMyUserRepository;
import id.zar.smartkantin.repository.IRoleRepository;
import id.zar.smartkantin.service.IRoleService;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleRepository roleRepo;
	@Autowired
	private IMyUserRepository userRepo;
	
	
	@Transactional
	@Override
	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}

	@Transactional
	@Override
	public List<Role> findRolesOfUser(MyUser user) {
		return roleRepo.findRolesOfUser(user);
	}

	@Transactional
	@Override
	public List<Role> addRoleToUser(MyUser user, Role role) {
		roleRepo.addRoleToUser(user, role);
		return findRolesOfUser(user);
	}

	@Transactional
	@Override
	public Role add(FormRole newRole) {
		return roleRepo.add(newRole);
	}

	@Transactional
	@Override
	public List<Role> getAll() {
		return roleRepo.getAll();
	}

	@Transactional
	@Override
	public List<Role> addRoleToUser(UUID userId, UUID roleId) {
		var user = userRepo.getById(userId);
		if(user == null)
		{
			return null;
		}
		var role = roleRepo.getById(roleId);
		if(role == null)
		{
			return null;
		}
		
		return addRoleToUser(user, role);
	}

}
