<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="<%=basePath%>css/jqueryui/jquery-ui.css">
	<link rel="stylesheet" href="<%=basePath%>css/form-style.min.css">
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/require.js"></script>
	<script src="<%=basePath%>js/require-config.js"></script>
	<link rel="stylesheet" href="<%=basePath%>css/login.css"/>
  </head>
  	
  <body>
    <div class="main">
         <form action="userAction$login" id="login-form" method="post">
             <div class="login-panel">
                 <div class="line-item fm-title">
                    Application Access
                 </div>
                 <div class="line-item">
                     <input type="text" name="user.name" id="user_name" placeholder="user_name"/>
                     <em class="tip"></em>
                 </div>
                 <div class="line-item">
                     <input type="password" name="user.password" id="user_password" placeholder="user_password"/>
                     <em class="tip"></em>
                 </div>
             </div>
             <div class="login-handle">
                 <span class="btn-switch">Auto Log In</span>
                 <input type="hidden" name="auto_login" id="auto_login" value="false"/>
                 <input type="submit" value="Log In" class="btn-submit"/>
             </div>
         </form>
     </div>
     <script type="text/javascript" src="js/login.js"></script>
     <script>
     	var errorInfo = "";
     	if(errorInfo){
     		alert(errorInfo);
     	}
     </script>
  </body>
</html>
