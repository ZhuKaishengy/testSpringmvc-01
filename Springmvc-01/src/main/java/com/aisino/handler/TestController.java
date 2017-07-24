package com.aisino.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.aisino.domain.Person;
import com.aisino.domain.User;
import com.aisino.viewResolver.ExcelViewResolver;
import com.aisino.viewResolver.PDFView;

@Controller
@RequestMapping("/TestController")
@SessionAttributes(value="user")
public class TestController {

	private static final String SUCCESS = "success";
	
	@ModelAttribute(value="user")
	public User getUser(@RequestParam(value="userId",required=false) String userId){
		if("".equals(userId) || userId == null){
			return new User("zhukaisheng", "123", "zhukaisheng@aisino.com", 23);
					
		}else{
			return new User("sunjiaxin", "123", "sunjiaxin@aisino.com", 24);
		}
	}
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(Map<String , Object> map ,@ModelAttribute("user") User user){
		map.put("user", user);
		return SUCCESS;
	}
	
	@ModelAttribute("personList")
	public List<Person> getList(){
		/**
		 * 准备model
		 */
		Person p1 = new Person("001", "zks", "10");
		Person p2 = new Person("002", "six", "11");
		Person p3 = new Person("003", "haha", "12");
		List<Person> list = new ArrayList<Person>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		return list;
	}
	
	@RequestMapping(value="/excel")
	public ModelAndView testExcelDownload(@ModelAttribute("personList") List<Person> list){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("person", list);
		ModelAndView modelAndView = new ModelAndView(new ExcelViewResolver(), map);
		return modelAndView;
	}
	@RequestMapping(value="/pdf")
	public ModelAndView testPDFDownload(@ModelAttribute("personList") List<Person> list){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("person", list);
		ModelAndView modelAndView = new ModelAndView(new PDFView(), map);
		return modelAndView;
	}
}
