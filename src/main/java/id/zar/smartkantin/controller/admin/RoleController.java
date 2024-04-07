package id.zar.smartkantin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.zar.smartkantin.DbModel.Role;
import id.zar.smartkantin.RequestModel.FormRole;
import id.zar.smartkantin.service.IRoleService;

@RestController
@RequestMapping("/api/admin/role")
public class RoleController {
	@Autowired
	private IRoleService svc;
	
	@PostMapping("/add")
	public Role add(@RequestBody FormRole role)
	{
		var result = svc.add(role);
		return result;
	}
}
