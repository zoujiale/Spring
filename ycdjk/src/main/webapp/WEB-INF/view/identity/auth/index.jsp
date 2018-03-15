<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>迎春OA办公后台</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${pgc }/adminlte/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pgc }/adminlte/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pgc }/adminlte/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pgc }/adminlte/dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="${pgc }/adminlte/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="${pgc}/adminlte/index2.html"><b>迎春办公</b>OA</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg" id="p_state" ></p>

			<form id="frm_login" >
				<div class="form-group has-feedback">
					<input type="email" class="form-control" placeholder="用户名/邮箱" name="username">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="password" class="form-control" placeholder="Password">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input id="ck_re" type="checkbox" name="remember" value="0"> Remember Me
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button id="btn_login" type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

			
			<!-- /.social-auth-links -->

			

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 3 -->
	<script src="${pgc}/adminlte/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="${pgc}/adminlte/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="${pgc}/adminlte/plugins/iCheck/icheck.min.js"></script>
	<script>
		$(function() {
			
			/* 为登录请求使用ajax */
			
			$("#btn_login").click(function(){
				var seralize = $("#frm_login").serialize();
				$("#p_state").val();
				
				if ($('#ck_re:checked')) {
					// 如果选中就为true
					$("#ck_re").val("1");
				}
				$.post("${pgc}/identity/auth",seralize ,
				          function(message){
								console.log(message.state)
								if (message.state ==true) {
									// 暂且定义新闻为主页
									window.location.href = "${pgc}/commons/index";
								}else{
									$("#p_state").text(message.message);
								}
								
				          });
			});
			
			$('input').iCheck({
				// 如果打勾为true
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%', /* optional */
				
			});
		});
	</script>
</body>
</html>