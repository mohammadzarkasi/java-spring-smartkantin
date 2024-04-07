package id.zar.smartkantin.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.repository.IMyUserRepository;
import id.zar.smartkantin.repository.IRoleRepository;
import id.zar.smartkantin.service.IMyUserService;
import id.zar.smartkantin.service.IRoleService;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IMyUserService userSvc;
	@Autowired
	private IRoleService roleSvc;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		var user = userSvc.getByUsername(username);
		if(user == null)
		{
			user = userSvc.getByEmail(username);
			if(user == null)
			{
				throw new UsernameNotFoundException("username/email tidak ditemukan");
			}
		}
		
		var roles = roleSvc.findRolesOfUser(user);
		
		return new User(user.getUsername(),user.getPassword(), mapRolesToAuthority(roles));
	}
	
	private Collection<GrantedAuthority> mapRolesToAuthority(List<Role> roles)
	{
		return roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
