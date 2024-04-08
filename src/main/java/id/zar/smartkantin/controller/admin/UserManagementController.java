package id.zar.smartkantin.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.RequestModel.FormAddRoleToUser;
import id.zar.smartkantin.ResponseModel.ResponseMyUser;
import id.zar.smartkantin.service.IMyUserService;
import id.zar.smartkantin.service.IRoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/admin/user-management")
public class UserManagementController {
	@Autowired
	private IMyUserService userSvc;
	@Autowired
	private IRoleService roleSvc;
	
	@GetMapping("")
	public List<ResponseMyUser> getAll()
	{
		var result = userSvc.getAll();
		return result.stream().map((u) -> ResponseMyUser.fromMyUser(u)).toList();
	}
	
	@PostMapping("/add-role")
	public List<Role> addRoleToUser(@RequestBody FormAddRoleToUser form) throws Exception
	{
		var result = roleSvc.addRoleToUser(form.getUserId(), form.getRoleId());
		if(result == null)
		{
			throw new Exception("user atau role tidak valid");
		}
		return result;
	}
}
