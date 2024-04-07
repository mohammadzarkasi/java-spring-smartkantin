package id.zar.smartkantin.repository;

import java.util.List;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.RequestModel.FormRole;

public interface IRoleRepository {
	Role findByName(String name);
	
	List<Role> findRolesOfUser(MyUser user);
	
	void addRoleToUser(MyUser user, Role role);
	
	Role add(FormRole newrole);
}
