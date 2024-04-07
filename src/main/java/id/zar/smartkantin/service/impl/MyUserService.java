package id.zar.smartkantin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.RequestModel.FormLogin;
import id.zar.smartkantin.RequestModel.FormRegister;
import id.zar.smartkantin.repository.IMyUserRepository;
import id.zar.smartkantin.repository.IRoleRepository;
import id.zar.smartkantin.service.IMyUserService;

@Service
public class MyUserService implements IMyUserService{

	@Autowired
	private IMyUserRepository repo;
	@Autowired
	private IRoleRepository roleRepo;
	
	@Transactional
	@Override
	public MyUser register(FormRegister form) {
		var userByUsername = repo.getByUsername(form.getUsername());
		if(userByUsername != null)
		{
			return null;
		}
		var userByEmail = repo.getByEmail(form.getEmail());
		if(userByEmail != null)
		{
			return null;
		}
//		var existingUser = login(form);
//		if(existingUser != null)
//		{
//			return null;
//		}
		var newUser = repo.add(form.asMyUser());
		
		var defaultRole = roleRepo.findByName("USER");
		if(defaultRole != null)
		{			
			roleRepo.addRoleToUser(newUser, defaultRole);
		}
		
		
		return newUser;
	}

	@Override
	public MyUser login(FormLogin form) 
	{
		var user = repo.getByEmail(form.getEmail());
		if(user == null)
		{
			user = repo.getByUsername(form.getUsername());
			if(user == null)
			{
				return null;
			}
		}
		

//		cocockkan password disini
		
		return user;
//		var userByEmail = repo.getByEmail(form.getEmail());
//		if(userByEmail != null)
//		{
//			return userByEmail;
//		}
//		
//		var userByUsername = repo.getByUsername(form.getUsername());
//		if(userByUsername != null)
//		{
//			return userByUsername;
//		}
//		
//		return null;
	}

	@Override
	public MyUser getByUsername(String username) {
		return repo.getByUsername(username);
	}

	@Override
	public MyUser getByEmail(String email) {
		return repo.getByEmail(email);
	}

}
