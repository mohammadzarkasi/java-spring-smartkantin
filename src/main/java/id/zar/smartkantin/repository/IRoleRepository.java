package id.zar.smartkantin.repository;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.RequestModel.FormRole;

public interface IRoleRepository {
	Role findByName(String name);
	Role getById(UUID id);
	
	List<Role> findRolesOfUser(MyUser user);
	List<Role> getAll();
	
	void addRoleToUser(MyUser user, Role role);
	
	Role add(FormRole newrole);
}
