package id.zar.smartkantin.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.zar.smartkantin.DbModel.Role;
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
		System.out.println("custom user detail service, load user.... " + username);
		
		var user = userSvc.getByUsernameOrEmail(username);
		if(user == null)
		{
//			user = userSvc.getByEmail(username);
//			if(user == null)
//			{
				throw new UsernameNotFoundException("username/email tidak ditemukan");
//			}
		}
		
		var roles = roleSvc.findRolesOfUser(user);
		System.out.println("user is: " + user.getUsername());
		System.out.print("roles: ");
		for(var role : roles)
		{
			System.out.print(role.getName() + ", ");
		}
		System.out.println();
		
//		return new User(user.getUsername(),user.getPassword(), mapRolesToAuthority(roles));
		return new CustomUser(user.getId(), user.getUsername(), user.getPassword(), mapRolesToAuthority(roles));
	}
	
	private Collection<GrantedAuthority> mapRolesToAuthority(List<Role> roles)
	{
		return roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
