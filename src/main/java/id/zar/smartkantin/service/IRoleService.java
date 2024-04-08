package id.zar.smartkantin.service;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.RequestModel.FormRole;

public interface IRoleService {
	Role findByName(String name);
	
	List<Role> findRolesOfUser(MyUser user);
	
	List<Role> addRoleToUser(MyUser user, Role role);
	List<Role> addRoleToUser(UUID userId, UUID roleId);
	List<Role> getAll();
	
	Role add(FormRole newRole);
	
}
