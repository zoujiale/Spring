<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>
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
<h1>${SessionCode }</h1>
	<div class="container">
		<form class="form-signin" id="login_in">
			<h2 class="form-signin-heading">请登陆</h2>
			
			<label for="inputEmail" class="sr-only">账户名/邮箱/手机</label> 
   		  <input
				type="text" id="username" class="form-control"
				placeholder="账户名/邮箱/手机" required="" name="username">
			<!-- <p>sdf</p> -->
			
			<label for="inputPassword" class="sr-only">Password</label> <input
				type="password" id="inputPassword" class="form-control"
				placeholder="密码" required="" name="password">
			<!-- <p>sdf</p> -->
			<label for="inputText" class="sr-only">Password</label> <input
				type="text" name="code" class="form-control" id="code"
				placeholder="验证码" required="" style="margin-top: 20px;">
			<!-- <p style="margin-bottom: 10px;">sdf</p> -->
			
				<label class="sr-only" for="exampleInputAmount">验证码</label>
					<div  class="input-group-addon" >
						<img class="check-code" id="vimg" alt="" src="${pgc}/common/code">
					</div>
				

			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me"
					id="reb-bt"> 记住账户名
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="button">登陆</button>
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
		$("#vimg").bind("click", function() {
			var date = new Date();
			$(this).attr("src", '${pgc}/common/code/?' + date.getTime());
		});

		//检测键盘按回车
		$(document).keydown(function(event) {
			if (event.keyCode == 13) {
				$("form :input[type=button]").trigger("click");
			}
		});

		// 登陆事件
		$("form :input[type=button]").bind("click", function() {

			var userId = $("form :input[name = username]").val();
			var passWord = $("form :input[name = password]").val();
			var vcode = $("form :input[name = code]").val();
				
			$("p[id!='']").remove();
			// 定义一个校验结果 
			var msg = "";
			if (!/^\w{2,20}$/.test(userId.trim())) {
				msg = "登录名必须是2-20个的字符";
				
				if ($("p[id!='']").length!=1) {
					$("#username").after("<p id='a' style='color:#a94442 '>"+msg + "</p>"); 
				}
				
			} else if (!/^\w{6,20}$/.test(passWord)) {
				msg = "密码必须是6-20个的字符";
				if ($("p[id!='']").length!=1) {
					$("#inputPassword").after("<p id='b'style='color:#a94442'>"+msg + "</p>"); 
				}
				
			} else if (!/^\w{4}$/.test(vcode)) {
				msg = "验证码格式不正确";
				if ($("p[id!='']").length!=1) {
					$("#code").after("<p id='c' style='color:#a94442'>"+msg + "</p>");
				}
			}
			if (msg != "") {
				return; // 结束程序 
			}
				
			

			var url = "${pgc}/common/index/";   // 以Post 请求请求这个Action
			var data = $("#login_in").serialize();
			$.ajax({
				url : url,
				type : 'post',
				data : data,
				dataType : 'json',
				success : function(data) {
					console.log(data);
					if (data.state) {
						window.location.href = '${pgc}/common/index'  // 以Get 请求请求这个Action
					} else {
						var message = data.message;
						console.log(message);
						if (message=="验证码错误") {
								 if ($("p[id!='']").length!=1) { 
									$("#code").after("<p id='c' style='color:#a94442'>"+message + "</p>");
								 } 
							}else{
								$("#inputPassword").after("<p id='c' style='color:#a94442'>"+message + "</p>");
							}
							
						
					}
				}

			});
			$("#vimg").trigger("click");
			$("#code").val("");
			$("#inputPassword").val(""); 
		});
	})
</script>
</html>