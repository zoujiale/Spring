<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>
<!-- =============================================== -->
	<aside class="main-sidebar">
				<!-- sidebar: style can be found in sidebar.less -->
				<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 446px;"><section class="sidebar" style="overflow: hidden; width: auto; height: 446px;">
					<!-- Sidebar user panel -->
					<div class="user-panel">
						<div class="pull-left image">
							<img src="${pgc }/index/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
						</div>
						<div class="pull-left info">
							<p>Alexander Pierce</p>
							<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
						</div>
					</div>
					<!-- search form -->
					<form action="#" method="get" class="sidebar-form">
						<div class="input-group">
							<input type="text" name="q" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
						</div>
					</form>
					<!-- /.search form -->
					<!-- sidebar menu: : style can be found in sidebar.less -->
					<ul class="sidebar-menu tree" data-widget="tree">
						<li class="header">MAIN NAVIGATION</li>
						<li class="treeview menu-open">
							<a href="#">
								<i class="fa fa-dashboard"></i> <span>用户管理</span>
								<span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
							</a>
							<ul class="treeview-menu" style="display: block;">
								<li>
									<a href="${pgc }/commons/index"><i class="fa fa-circle-o"></i> 个人信息</a>
								</li>
								<li>
									<a href="${pgc }/identity/operation"><i class="fa fa-circle-o"></i> 用户列表</a>
								</li>
							</ul>
						</li>
					
						
						<li class="header">LABELS</li>
						<li>
							<a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a>
						</li>
						<li>
							<a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a>
						</li>
						<li>
							<a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a>
						</li>
					</ul>
				</section><div class="slimScrollBar" style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: -432.281px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 218.589px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div></div>
				<!-- /.sidebar -->
			</aside>

			<!-- =============================================== -->
