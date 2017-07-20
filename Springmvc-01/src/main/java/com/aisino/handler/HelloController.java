package com.aisino.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
