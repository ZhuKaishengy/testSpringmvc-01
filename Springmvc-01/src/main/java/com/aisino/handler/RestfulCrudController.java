package com.aisino.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aisino.domain.Address;
import com.aisino.domain.User;

@Controller
@RequestMapping("/RestfulCrudController")
public class RestfulCrudController {

	@ModelAttribute("users")
	public List<User> getUsers(){
		//select * from users
		Address address1 = new Address("辽宁省", "沈阳市");
		Address address2 = new Address("辽宁省", "鞍山市");
		User u1 = new User("zks", "123", "zks@aisino.com", 21,address1);
		User u2 = new User("sjx", "456", "sjx@aisino.com", 22,address2);
		List<User> users = new ArrayList<User>();
		users.add(u1);
		users.add(u2);
		return users;
	}
	@RequestMapping("/user")
	public String show(@ModelAttribute("users") List<User> users,Map<String, Object> map){
		map.put("users", users);
		return "user-show";
	}
	/*@RequestMapping("/user")
	public String editForm(){
		return "";
	}*/
}
