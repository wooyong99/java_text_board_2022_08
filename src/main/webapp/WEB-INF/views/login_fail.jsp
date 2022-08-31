<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-08-31
  Time: AM 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login_fail</title>
</head>
<body>
    <%
        String input_id=(String)request.getAttribute("input_id");
        String input_pw=(String)request.getAttribute("input_pw");
    %>
    <%  if(input_id.equals("admin") && !input_id.equals("1234")){ %>
        <script type="text/javascript">
            alert("비밀번호가 틀렸습니다.");
            location.href="login";
        </script>

    <% }else{%>
        <script type="text/javascript">
            alert("존재하지 않는 아이디입니다.");
            location.href="login";
        </script>

    <%}%>

</body>
</html>
