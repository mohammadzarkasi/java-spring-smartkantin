package id.zar.smartkantin.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.RequestModel.FormLogin;
import id.zar.smartkantin.ResponseModel.ResponseLogin;
import id.zar.smartkantin.ResponseModel.ResponseMyUser;
import id.zar.smartkantin.security.JWTGenerator;
import id.zar.smartkantin.service.IMyUserService;
import id.zar.smartkantin.service.IRoleService;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {
	
	@Autowired
	private IMyUserService svc;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private IRoleService roleSvc;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JWTGenerator jwtGenerator;
	
	@PostMapping("")
	public ResponseLogin login(@RequestBody FormLogin form)
	{
		var user = svc.login(form);
		
		if(user == null)
		{
			var loginFailed = new ResponseLogin();
			loginFailed.setStatus(false);
			loginFailed.setMsg("user tidak ditemukan");
			
			return loginFailed;
		}
		
		var username = user.getUsername();
		if(username.isEmpty()) 
		{
			username = user.getEmail();
		}
		
		var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, form.getPassword()));
		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		var token = jwtGenerator.generateToken(authentication);
		
		var loginSuccess = new ResponseLogin();
		loginSuccess.setStatus(true);
		loginSuccess.setMsg("login sukses");
		loginSuccess.setToken(token);
		
		return loginSuccess;
	}
}
