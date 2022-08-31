<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-08-30
  Time: PM 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous"/>
    <link href="css/Login.css?ver=1" rel="stylesheet">
    <title>Login</title>
</head>
<body>
<h1><a href="/">Web Reference TextBoard</a></h1>
<form action="/login_check">
    <div>아이디 : <input type="text" name="user_id" method="post" placeholder="ID를 입력해주세요."/></div>
    <div>비밀번호 : <input type="password" name="user_pw" placeholder="Password를 입력해주세요."/></div>
    <div><input id="login_btn" type="submit" value="Login"/></div>
</form>

</body>
</html>
