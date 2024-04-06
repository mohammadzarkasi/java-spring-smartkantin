package id.zar.smartkantin.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.RequestModel.FormRegister;
import id.zar.smartkantin.ResponseModel.ResponseMyUser;
import id.zar.smartkantin.service.IMyUserService;

@RestController
@RequestMapping("/api/auth/register")
public class RegisterController {
	
	@Autowired
	private IMyUserService svc;
	
	@PostMapping("")
	public ResponseMyUser register(@RequestBody FormRegister form) throws Exception
	{
		if(form.getPassword().equals(form.getPassword2()) == false)
		{
			throw new Exception("password tidak sama");
		}
		var result = svc.register(form);
		if(result == null)
		{
			throw new Exception("username/email sudah dipakai");
		}
		return ResponseMyUser.fromMyUser(result);
	}
}
