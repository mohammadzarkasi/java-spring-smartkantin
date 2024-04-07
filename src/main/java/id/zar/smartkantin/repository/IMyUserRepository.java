package id.zar.smartkantin.repository;

import id.zar.smartkantin.DbModel.MyUser;

public interface IMyUserRepository {
	MyUser add(MyUser user);
	MyUser getByUsername(String username);
	MyUser getByEmail(String email);
	MyUser getByUsernameOrEmail(String usernameOrEmail);
}
