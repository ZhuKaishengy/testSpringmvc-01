package com.aisino.test;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class Test {

	HttpClient httpClient = new HttpClient();
	/**
	 * post请求
	 * @throws HttpException
	 * @throws IOException
	 */
	@org.junit.Test
	public void testPost() throws HttpException, IOException{
		 PostMethod postMethod = new PostMethod("http://localhost:8080/Springmvc-01/helloController/testRequestMapping");
		 int status = httpClient.executeMethod(postMethod);
		 System.out.println(status);
		 if(status == HttpStatus.SC_OK){
			String str = postMethod.getResponseBodyAsString();
			 System.out.println(str);
		 }
	}
	/**
	 * get请求
	 * @throws HttpException
	 * @throws IOException
	 */
	@org.junit.Test
	public void testGet() throws HttpException, IOException{
		GetMethod getMethod = new GetMethod("http://localhost:8080/Springmvc-01/helloController/testRequestMapping");
		int status = httpClient.executeMethod(getMethod);
		System.out.println(status);
		if(status == HttpStatus.SC_OK){
			String str = getMethod.getResponseBodyAsString();
			 System.out.println(str);
		}
	}
	
	@org.junit.Test
	public void testrequestHeaderAndParam() throws HttpException, IOException{
		 PostMethod postMethod = new PostMethod("http://localhost:8080/Springmvc-01/helloController/testRequestMappingParamsAndHeaders");
		 postMethod.setParameter("username", "sjx");
//		 postMethod.setParameter("password", "10");
		 Header header = new Header("zks", "shuai");
		postMethod.setRequestHeader(header);
		 int status = httpClient.executeMethod(postMethod);
		 System.out.println(status);
		 if(status == HttpStatus.SC_OK){
			String str = postMethod.getResponseBodyAsString();
			 System.out.println(str);
		 }
	}
}
