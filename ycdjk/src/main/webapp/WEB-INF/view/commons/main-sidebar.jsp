<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar" style="height: auto;"> <!-- Sidebar user panel -->
<div class="user-panel">
	<div class="pull-left image">
		<img src="${pgc }/adminlte/dist/img/user2-160x160.jpg" class="img-circle"
			alt="User Image">
	</div>
	<div class="pull-left info">
		<p>${user.userName }</p>
		<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
	</div>
</div>
<!-- search form -->
<form action="#" method="get" class="sidebar-form">
	<div class="input-group">
		<input type="text" name="q" class="form-control"
			placeholder="Search..."> <span class="input-group-btn">
			<button type="submit" name="search" id="search-btn"
				class="btn btn-flat">
				<i class="fa fa-search"></i>
			</button>
		</span>
	</div>
</form>
<!-- /.search form --> <!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu tree" data-widget="tree">
	<li class="header">MAIN NAVIGATION</li>
	<li class="treeview"><a href="#"> <i class="fa fa-dashboard"></i>
			<span>系统管理模块</span> <span class="pull-right-container"> <i
				class="fa fa-angle-left pull-right"></i>
		</span>
	</a>
		<ul class="treeview-menu">
			<li><a href="${pgc }/identity/management"><i class="fa fa-circle-o"></i>
					用户管理</a></li>
			<li><a href="../../index2.html"><i class="fa fa-circle-o"></i>
					Dashboard v2</a></li>
		</ul></li>
	
		
	<li class="header">LABELS</li>
	<li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
	<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
	<li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>
</ul>
</section> <!-- /.sidebar --> </aside>