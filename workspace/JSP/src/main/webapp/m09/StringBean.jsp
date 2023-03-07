<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Using JavaBean with JSP</title>
</head>
<body>
<h3>Using JavaBean with JSP</h3>
<jsp:useBean id="stringBean" class="com.lcpan.bean.StringBean" />
<ol>
	<li>
	<jsp:setProperty property="message" name="stringBean" value="This ia a test"/>
		Set and get property with jsp:setProperty:<br>
		<i><jsp:getProperty property="message" name="stringBean"/></i>
	<li><% stringBean.setMessage("Learning JSP is wonderful"); %>
		Set and get property with scriptlet:<br>
		<i>stringBean.getMessage()</i>	
</ol>
</body>
</html>