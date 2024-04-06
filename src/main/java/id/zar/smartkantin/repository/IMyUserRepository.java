package id.zar.smartkantin.repository;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.RequestModel.FormRegister;
import id.zar.smartkantin.ResponseModel.ResponseMyUser;

public interface IMyUserRepository {
	MyUser add(MyUser user);
	MyUser getByUsername(String username);
	MyUser getByEmail(String email);
}
