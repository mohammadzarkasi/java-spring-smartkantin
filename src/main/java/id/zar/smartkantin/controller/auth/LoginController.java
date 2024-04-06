package id.zar.smartkantin.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.RequestModel.FormLogin;
import id.zar.smartkantin.ResponseModel.ResponseMyUser;
import id.zar.smartkantin.service.IMyUserService;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {
	
	@Autowired
	private IMyUserService svc;
	
	@PostMapping("")
	public ResponseMyUser login(@RequestBody FormLogin form)
	{
		var user = svc.login(form);
		return ResponseMyUser.fromMyUser(user);
	}
}
