<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <a href="helloController">testGet<br>
    <form action="helloController" title="testPost" method="POST">
    	<input type="text" name="username"/>
    	<input type="submit" value="submit"/>
    </form>
    <form action="helloController/testPutMethod/1" title="testPut" method="POST">
    	<input type="hidden" name="_method" value="PUT"/>
    	<input type="submit" value="submit"/>
    </form>
    <form action="helloController/testDeleteMethod/2" title="testDelete" method="post" />
		<input type="hidden" name="_method" value="DELETE"/>
    	<input type="submit" value="submit"/>
    </form>
    <a href="helloController/testRequestParam?username=zks&&password=123">testRequestParam</a>
  </body>
</html>
