<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">

<title>超级无敌铁砂掌无敌博客登陆看你怎么说</title>
<!-- Bootstrap core CSS -->
<link href="${pgc}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pgc}/bootstrap/css/signin.css" rel="stylesheet">
<script src="${pgc}/js/jquery-3.2.1.min.js"></script>
<script src="${pgc}/js/jquery.cookie.js"></script>

</head>
<body>
	<div class="container">
		<form class="form-signin">
			<h2 class="form-signin-heading">请登陆</h2>
			<label for="inputEmail" class="sr-only">账户名/邮箱/手机</label> <input
				type="text" id="username" class="form-control"
				placeholder="账户名/邮箱/手机" required=""> <label for="inputPassword"
				class="sr-only">Password</label> <input type="password"
				id="inputPassword" class="form-control" placeholder="密码" required="">
			<label for="inputText" class="sr-only">Password</label> 
		
				<input type="text" class="form-control" id="exampleInputAmount" placeholder="验证码" required="">
				
				
				 <span
					class="input-group-addon" id="basic-addon2"><img
					class="check-code" id="vimg" alt="" src="${pgc}/common/code"></span>
			
		
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me"
					id="reb-bt"> 记住账户名
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
		</form>
	</div>
	
</body>
<script type="text/javascript">
	$(function() {
		
		//为记住账号设置Cookie
		var cook_name = 'username';
		$("#username").change(function() {
			var username = $("#username").val();
			var isCheck = $("#reb-bt").prop("checked");
			if (isCheck) {
				if (username != null && username != "") {
					$.cookie(cook_name, username, {
						path : '/',
						expires : 10
					})
				}
			}
		});

		if ($.cookie(cook_name) == null || $.cookie(cook_name) == "") {
			$("#username").val("");
			$("#reb-bt").attr("checked", false);
		} else {
			$("#username").val($.cookie(cook_name));
			$("#reb-bt").attr("checked", true);
		}

		$("#reb-bt").bind("click", function() {
			var username = $("#username").val();
			username = $.trim(username);
			var ischeck = $(this).prop("checked");
			if (ischeck) {

				if (username != "" && username != null) {
					$.cookie(cook_name, username, {
						path : '/',
						expires : 10
					})
				} else {
					$("#username").val("");
				}

			} else {
				$.cookie(cook_name, "", {
					path : '/'
				});
			}
		});
		//刷新验证码功能
		$("#vimg").bind("click",function(){
			var date = new Date();
			$(this).attr("src",'${pgc}/common/code/?'+date.getTime());
		});
	})
</script>
</html>