package id.zar.smartkantin.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.RequestModel.FormRole;
import id.zar.smartkantin.service.IRoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/admin/role")
@SecurityRequirement(name = "Authorization")
public class RoleController {
	@Autowired
	private IRoleService svc;
	
	@GetMapping("")
	public List<Role> getAll()
	{
		var result = svc.getAll();
		return result;
	}
	
	@PostMapping("/add")
	public Role add(@RequestBody FormRole role)
	{
		var result = svc.add(role);
		return result;
	}
}
