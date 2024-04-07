package id.zar.smartkantin.controller.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.RequestModel.FormLogin;
import id.zar.smartkantin.ResponseModel.ResponseLogin;
import id.zar.smartkantin.security.CustomUser;
import id.zar.smartkantin.security.JWTGenerator;
import id.zar.smartkantin.service.IMyUserService;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {
	
	@Autowired
	private IMyUserService svc;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTGenerator jwtGenerator;
	
	@PostMapping("")
	public ResponseLogin login(@RequestBody FormLogin form)
	{
//		var user = svc.login(form);
//		
//		if(user == null)
//		{
//			var loginFailed = new ResponseLogin();
//			loginFailed.setStatus(false);
//			loginFailed.setMsg("user tidak ditemukan");
//			
//			return loginFailed;
//		}
//		
//		var username = user.getUsername();
//		if(username.isEmpty()) 
//		{
//			username = user.getEmail();
//		}
		
		if((form.getUsername() == null || form.getUsername().isEmpty()) && (form.getEmail() != null || form.getEmail().isEmpty()))
		{
			return new ResponseLogin(false,"username/email tidak boleh kosong");
		}
		if(form.getPassword().isEmpty())
		{
			return new ResponseLogin(false, "password tidak boleh kosong");
		}
		
		
		System.out.println("check username dan password cocok?");
		
//		kode ini bertugas mengecek username dan password yg diinput user dan yg disimpan di db
//		var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, form.getPassword()));
		
		Authentication authentication = null;
		if( form.getUsername() != null && form.getUsername().isEmpty()==false)
		{
			try
			{
				authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
			}
			catch(Exception e)
			{
				System.out.println("gagal login menggunakan username");
	//			System.out.println(e);
			}
		}
		
		if(authentication == null && form.getEmail() != null && form.getEmail().isEmpty() == false)
		{
			try
			{
				authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword()));
			}
			catch(Exception e)
			{
				System.out.println("gagal login menggunakan email");
//				System.out.println(e);
			}
		}
		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		if(authentication == null)
		{
			var loginFailed = new ResponseLogin(false, "user tidak ditemukan atau password tidak cocok");
//			loginFailed.setStatus(false);
//			loginFailed.setMsg("user tidak ditemukan atau password tidak cocok");
			
			return loginFailed;
		
		}
		
		UUID userId = new UUID(0,0);
		
		if(authentication instanceof CustomUser)
		{
			userId = ((CustomUser) authentication).getUserId();
		}
		
		var token = jwtGenerator.generateToken(authentication, userId);
//		var token = jwtGenerator.generateToken(username, user.getId());
		
		var loginSuccess = new ResponseLogin(true, "login sukses");
//		loginSuccess.setStatus(true);
//		loginSuccess.setMsg("login sukses");
		loginSuccess.setToken(token);
		
		return loginSuccess;
	}
}
