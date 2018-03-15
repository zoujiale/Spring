<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>个人首页</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="/WEB-INF/view/commons/main-css.jsp"></jsp:include>

<!-- 富文本的样式表 -->
<link rel="stylesheet"
	href="${pgc }/adminlte/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">


</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper">

		<jsp:include page="/WEB-INF/view/commons/main-header.jsp"></jsp:include>

		<!-- =============================================== -->

		<!-- Left side column. contains the sidebar -->
		<jsp:include page="/WEB-INF/view/commons/main-sidebar.jsp"></jsp:include>
		<!-- =============================================== -->



		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					个人首页 <small>it all starts here</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">个人首页</li>
				</ol>
			</section>

			<section class="content">
				<!-- Small boxes (Stat box) -->
				<div class="row">
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-aqua">
							<div class="inner">
								<h3>150</h3>

								<p>新的审批</p>
							</div>
							<div class="icon">
								<i class="ion ion-bag"></i>
							</div>
							<a href="#" class="small-box-footer">More info <i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-green">
							<div class="inner">
								<h3>
									53<sup style="font-size: 20px">%</sup>
								</h3>

								<p>Bounce Rate</p>
							</div>
							<div class="icon">
								<i class="ion ion-stats-bars"></i>
							</div>
							<a href="#" class="small-box-footer">More info <i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-yellow">
							<div class="inner">
								<h3>44</h3>

								<p>用户管理</p>
							</div>
							<div class="icon">
								<i class="ion ion-person-add"></i>
							</div>
							<a href="#" class="small-box-footer">More info <i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-red">
							<div class="inner">
								<h3>65</h3>

								<p>Unique Visitors</p>
							</div>
							<div class="icon">
								<i class="ion ion-pie-graph"></i>
							</div>
							<a href="#" class="small-box-footer">More info <i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
				</div>
				<!-- /.row -->
				<!-- Main row -->
				<div class="row">
					<!-- Left col -->
					<section class="col-lg-7 connectedSortable">
						<!-- Custom tabs (Charts with tabs)-->
						<div class="nav-tabs-custom">
							<!-- Tabs within a box -->
							<ul class="nav nav-tabs pull-right">
								<li class="active"><a href="#revenue-chart"
									data-toggle="tab">Handle</a></li>
								<li class="pull-left header"><i class="fa fa-inbox"></i>
									待我审批</li>
							</ul>
							<div class="tab-content no-padding">
								<!-- Morris chart - Sales -->
								<div class="chart tab-pane active" id="revenue-chart"
									style="position: relative; height: 300px;"></div>

							</div>
						</div>
						<!-- /.nav-tabs-custom -->

						<!-- TO DO List -->
						<div class="box box-primary">
							<div class="box-header ui-sortable-handle" style="cursor: move;">
								<i class="ion ion-clipboard"></i>

								<h3 class="box-title">待办事项列表</h3>

								<div class="box-tools pull-right">
									<ul class="pagination pagination-sm inline">
										<li><a href="#">«</a></li>
										<li><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">»</a></li>
									</ul>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<ul class="todo-list ui-sortable">
									<li class="">
										<!-- drag handle --> <span class="handle ui-sortable-handle">
											<i class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i>
									</span> <!-- checkbox --> <input type="checkbox" value=""> <!-- todo text -->
										<span class="text">设计一个很好的主题</span> <!-- Emphasis label --> <small
										class="label label-danger"><i class="fa fa-clock-o"></i>
											2 分钟</small> <!-- General tools such as edit or delete-->
										<div class="tools">
											<i class="fa fa-edit"></i> <i class="fa fa-trash-o"></i>
										</div>
									</li>
									<li class=""><span class="handle ui-sortable-handle">
											<i class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i>
									</span> <input type="checkbox" value=""> <span class="text">使主题反应快速</span>
										<small class="label label-info"><i
											class="fa fa-clock-o"></i> 4 小时</small>
										<div class="tools">
											<i class="fa fa-edit"></i> <i class="fa fa-trash-o"></i>
										</div></li>
									<li><span class="handle ui-sortable-handle"> <i
											class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i>
									</span> <input type="checkbox" value=""> <span class="text">让主题闪耀如星星</span>
										<small class="label label-warning"><i
											class="fa fa-clock-o"></i> 1 天</small>
										<div class="tools">
											<i class="fa fa-edit"></i> <i class="fa fa-trash-o"></i>
										</div></li>
									<li><span class="handle ui-sortable-handle"> <i
											class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i>
									</span> <input type="checkbox" value=""> <span class="text">让主题闪耀如星星</span>
										<small class="label label-success"><i
											class="fa fa-clock-o"></i> 3 天</small>
										<div class="tools">
											<i class="fa fa-edit"></i> <i class="fa fa-trash-o"></i>
										</div></li>
									<li><span class="handle ui-sortable-handle"> <i
											class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i>
									</span> <input type="checkbox" value=""> <span class="text">
											检查您的邮件和通知</span> <small class="label label-primary"><i
											class="fa fa-clock-o"></i> 1 周</small>
										<div class="tools">
											<i class="fa fa-edit"></i> <i class="fa fa-trash-o"></i>
										</div></li>
									<li><span class="handle ui-sortable-handle"> <i
											class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i>
									</span> <input type="checkbox" value=""> <span class="text">让主题闪耀如星星</span>
										<small class="label label-default"><i
											class="fa fa-clock-o"></i> 1 月</small>
										<div class="tools">
											<i class="fa fa-edit"></i> <i class="fa fa-trash-o"></i>
										</div></li>
								</ul>
							</div>
							<!-- /.box-body -->
							<div class="box-footer clearfix no-border">
								<button type="button" class="btn btn-default pull-right">
									<i class="fa fa-plus"></i> 添加项
								</button>
							</div>
						</div>
						<!-- /.box -->

						<!-- /.Left col -->
					</section>
					<!-- right col (We are only adding the ID to make the widgets sortable)-->
					<section class="col-lg-5 connectedSortable">

						<div class="box box-widget widget-user-2">
							<!-- Add the bg color to the header using any of the bg-* classes -->
							<div class="widget-user-header bg-green">
								<div class="widget-user-image">
									<img class="img-circle"
										src="${pgc }/adminlte/dist/img/user7-128x128.jpg"
										alt="User Avatar">
								</div>
								<!-- /.widget-user-image -->
								<h3 class="widget-user-username">${user.userName }</h3>
								<h5 class="widget-user-desc">IT</h5>
							</div>
							<div class="box-footer no-padding">
								<ul class="nav nav-stacked">
									<li><a href="#">Projects <span
											class="pull-right badge bg-blue">31</span></a></li>
									<li><a href="#">Tasks <span
											class="pull-right badge bg-aqua">5</span></a></li>
									<li><a href="#">Completed Projects <span
											class="pull-right badge bg-green">12</span></a></li>
									<li><a href="#">Followers <span
											class="pull-right badge bg-red">842</span></a></li>
								</ul>
							</div>
						</div>
						<div class="box box">
							<div class="box-header ui-sortable-handle" style="cursor: move;">
								<i class="fa fa-envelope"></i>

								<h3 class="box-title">快速电子邮件</h3>
								<!-- tools box -->
								<div class="pull-right box-tools">
									<button type="button" class="btn btn btn-sm"
										data-widget="remove" data-toggle="tooltip" title="Remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
								<!-- /. tools -->
							</div>
							<div class="box-body">
								<form action="#" method="post">
									<div class="form-group">
										<input type="email" class="form-control" name="emailto"
											placeholder="Email to:">
									</div>
									<div class="form-group">
										<input type="text" class="form-control" name="subject"
											placeholder="Subject">
									</div>
										<div class="box-body pad">
											<textarea class="textarea"
												style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid rgb(221, 221, 221); padding: 10px;"
												placeholder="Place some text here" name="mainBody">
									</textarea>
										</div>
								</form>
							</div>
							<div class="box-footer clearfix">
								<button type="button" class="pull-right btn btn-default"
									id="sendEmail">
									发送 <i class="fa fa-arrow-circle-right"></i>
								</button>
							</div>
						</div>
					</section>
				</div>
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 2.4.0
			</div>
			<strong>Copyright &copy; 2018 <a
				href="http://www.gzycdjk.com/">迎春大健康服务有限公司</a>.
			</strong> All rights reserved.
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li><a href="#control-sidebar-home-tab" data-toggle="tab"><i
						class="fa fa-home"></i></a></li>

				<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
						class="fa fa-gears"></i></a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-user bg-yellow"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Frodo Updated His
										Profile</h4>

									<p>New phone +1(800)555-1234</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-envelope-o bg-light-blue"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Nora Joined Mailing
										List</h4>

									<p>nora@example.com</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-file-code-o bg-green"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Cron Job 254
										Executed</h4>

									<p>Execution time 5 seconds</p>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Custom Template Design <span
										class="label label-danger pull-right">70%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger"
										style="width: 70%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Update Resume <span class="label label-success pull-right">95%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-success"
										style="width: 95%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Laravel Integration <span
										class="label label-warning pull-right">50%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-warning"
										style="width: 50%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Back End Framework <span class="label label-primary pull-right">68%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-primary"
										style="width: 68%"></div>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Report panel
								usage <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Allow mail
								redirect <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Other sets of options are available</p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Expose author
								name in posts <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Allow the user to show his name in blog posts</p>
						</div>
						<!-- /.form-group -->

						<h3 class="control-sidebar-heading">Chat Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Show me as
								online <input type="checkbox" class="pull-right" checked>
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Turn off
								notifications <input type="checkbox" class="pull-right">
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Delete chat
								history <a href="javascript:void(0)" class="text-red pull-right"><i
									class="fa fa-trash-o"></i></a>
							</label>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->


		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<jsp:include page="/WEB-INF/view/commons/main-js.jsp"></jsp:include>


	<!-- 富文本框控件 -->
	<script
		src="${pgc}/adminlte/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.sidebar-menu').tree();
			$('.textarea').wysihtml5();

		})
	</script>
</body>
</html>
