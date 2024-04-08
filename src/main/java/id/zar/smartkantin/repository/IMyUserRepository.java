package id.zar.smartkantin.repository;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.MyUser;

public interface IMyUserRepository {
	MyUser add(MyUser user);
	MyUser getById(UUID id);
	MyUser getByUsername(String username);
	MyUser getByEmail(String email);
	MyUser getByUsernameOrEmail(String usernameOrEmail);
	List<MyUser> getAll();
}
