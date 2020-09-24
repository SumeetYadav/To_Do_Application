<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<span>We have send a verification  code to your email please check your email</span>
        <form action="VerifyCode" method="post">
            <input type="text" name="authcode" >
            <input type="submit" style="background-color:skyblue;color:black;width:55px;height:27px;" value="verify">
        </form>
</body>
</html>