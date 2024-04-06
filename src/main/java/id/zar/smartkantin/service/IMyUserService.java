package id.zar.smartkantin.service;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.RequestModel.FormLogin;
import id.zar.smartkantin.RequestModel.FormRegister;

public interface IMyUserService {
	MyUser register(FormRegister form);
	MyUser login(FormLogin form);
}
