package com.aisino.handler;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.aisino.domain.User;

@SessionAttributes(value="user")
@RequestMapping("/helloController")
@Controller
public class HelloController {

	private static final String HELLO = "hello";
	
	@RequestMapping(value="/hello")
	public String hello(){
		
		return HELLO;
	}
	
	@RequestMapping(value="/testRequestMapping",method=RequestMethod.POST)
	public String testRequestMappingMethod(){
		return HELLO;
	}
	/**
	 * != 可以没有
	 * @return
	 */
	@RequestMapping(value="/testRequestMappingParamsAndHeaders",params={"username","password!=10"},headers="zks")
	public String testRequestMappingParamsAndHeaders(){
		return HELLO;
	}
	/**
	 * ant风格url
	 * ？ 匹配一个
	 * * 匹配任意
	 * ** 匹配多层目录
	 * @return
	 */
	@RequestMapping("/testAntUrl/*/zks")
	public String testAntUrl(){
		return HELLO;
	}
	/**	@PathVariable
	 * 映射url中参数
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") String id){
		System.out.println(id);
		return  HELLO;
	}
	/**
	 * rest风格uri，发送put和delete请求
	 * @param id
	 * @return
	 */
	@RequestMapping(value="testPutMethod/{id}",method=RequestMethod.PUT)
	@ResponseBody()
	public String testPutMethod(@PathVariable(value="id") int id){
		System.out.println(id);
		return HELLO;
	}
	
	@RequestMapping(value="testDeleteMethod/{id}",method=RequestMethod.DELETE)
	@ResponseBody()
	public String testDeleteMethod(@PathVariable(value="id") int id){
		System.out.println(id);
		return "redirect:/hello";
	}
//----------------------------获取http请求相关的参数------------------------------
	/**
	 * @RequestParam
	 * 映射请求参数
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/testRequestParam")
	public String testRequestParam(@RequestParam(value="username") String username,@RequestParam(value="password",required=false,defaultValue="sjx") String password){
		System.out.println("username:"+username+"\n"+"password:"+password);
		return HELLO;
	}
	/**
	 * User user
	 * 获取请求参数并封装成pojo
	 * @param user
	 * @return
	 */
	@RequestMapping("testPojo")
	public String testPojo(User user){
		System.out.println(user);
		return HELLO;
	}
	/**
	 * @RequestHeader
	 * 映射请求头
	 * @param userid
	 * @return
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="userid") int userid){
		System.out.println(userid);
		return HELLO;
	}
	/**
	 * @CookieValue
	 * 映射请求cookie
	 * @param sessionId
	 * @return
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue(value="JSESSIONID") String sessionId){
		System.out.println(sessionId);
		return HELLO;
	}
//	---------------------------------------------------------------------------------------
	/**
	 * 回调servlet源生api
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/testServletAPI")
	public String testServletAPI(HttpServletRequest req,HttpServletResponse resp){
		
		System.out.println("HttpServletRequest:"+req+"\n"+"HttpServletResponse:"+resp);
		return HELLO;
	}
//	---------------------------------将模型放入域对象中---------------------------------------------
	/**
	 * （请求域）第一种方式:ModelAndView
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(@RequestParam(value="username") String username){
		String viewName = "modelAndView";
		ModelAndView modelAndView = new ModelAndView(viewName);
		modelAndView.addObject("username", username);
		return modelAndView;
	}
	/**
	 * （请求域）第二种方式:BindingAwareModelMap
	 * 
	 * @param requestMap
	 * @return
	 */
	@RequestMapping("/testRequestMap")
	public String testRequestMap(Map<String, Object> requestMap){
		System.out.println(requestMap.getClass().getName());
		requestMap.put("date", new Date());
		return "testRequestMap";
	}
	/**
	 * （session域）@SessionAttributes(value="user")
	 * 在controller上加入如上注解，只能放在类上面
	 * @param requestMap
	 * @return
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> requestMap){
		User user = new User();
		user.setUsername("zhukaisheng");
		requestMap.put("user", user);
		return "testSessionAttributes";
	}
	
	public void getUser(@RequestParam(value="id",required=false) Integer id,Map<String, Object> map){
		if(id!=null){
			User user = new User();
			user.setUsername("hahahah");
		}
	}
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("修改："+user);
		return "testModelAttribute";
	}
	
}
